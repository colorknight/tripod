# Tripod介绍

​		Tripod用于对文本数据的实时匹配分析，其语法主要兼容了lucene语法，并做了微调。在很多文本处理的应用中，会有类似数据订阅的需求，即将客户关注的数据推送给客户。一种典型的实现是，我们会根据用户设定的关键词或规则，在lucene或elasticsearch中匹配数据，并最终将数据推送给客户。这种实现方式，要求文本数据必须已经建立好索引，访问时，会带来大量的IO消耗。各种基于定时策略的访问优化，都无法根本解决随着客户数量的增多而带来的这种资源消耗。Tripod能很好的改善这种应用需求，它支持对文本进行实时匹配，不会引起IO消耗。另外，它兼容lucene语法，可以与即有的基于lucene与elasticsearch的应用无缝整合，即如果采用lucene语法描述客户关注的信息，那么即可以使用Tripod解决实时推送问题，也可以实现从lucene或elasticsearch中对历史数据进行检索。下面将对Tripod进行详细介绍。

## Tripod工程

​       Tripod工程主要包括：tripod-meta与tripod-engine两个模块。tripod-meta模块主要实现了lucene语法的解析。该模块支持将一个遵照lucene语法编写的文本规则转换为一棵对象语法树。在语法树的基础上，可以更容易进行界面的配置表达，如：可以语法树基础上实现一个可视化的lucene语法编辑器，并由语法树反向生成一个语法文本串，交由lucene执行；tripod-engine模块实现了lucene语法的实时解释执。它将由tripod-meta解析成的语法树，翻译成一组可执行单元。调用接口，传入文档数据，tripod-engine模块就可以计算出当前文档的相关度了。在查看tripod工程的相关代码时，可以借鉴另外一个开源工程moql中提到的相关概念。工程主要包括：与两个模块。模块主要实现了语法的解析。该模块支持将一个遵照语法编写的文本规则转换为一棵对象语法树。在语法树的基础上，可以更容易进行界面的配置表达，如：可以语法树基础上实现一个可视化的语法编辑器，并由语法树反向生成一个语法文本串，交由lucene执行；模块实现了语法的实时解释执。它将由解析成的语法树，翻译成一组可执行单元。调用接口，传入文档数据，模块就可以计算出当前文档的相关度了。在查看工程的相关代码时，可以借鉴另外一个开源工程中提到的相关概念。

## Tripod设计

​       Tripod的接口设计借鉴了lucene中的概念，将每个待处理数据都视为一个文档。多数情况下，Tripod提供的接口都要求将文档以map的形式表示，如下接口，是Tripod中最为重要的一个接口：

```
/**
* 对map格式描述的文档数据进行匹配。
 * @param dataMap 待匹配数据，Map中的key为lucene规则中要处理的field;value为文本
 *                进行分词处理后的有序词组。每组数据会被视为一个文档，内部处理时会
 *                转换为DocumentEntity对象。
 * @param termDistance 是否以词距离进行匹配，缺省为true；若该值为false，表示以字
 *                     符距离进行距离计算。
 * @param hitMap 该对象是一个返出结果对象，它会被填充所有由规则命中的词、短语或一段
*               文本的起止位置。由于文档数据已被做过分词，文字空间上不再连续，当规
*               则中带有距离计算时，无法复原文本串，故只给出其起止位置。key值与
*               dataMap的key值含义相同。
 * @return 匹配的分值，若该值为负表示没有匹配
 */
public double match(Map<String, TermEntity[]> dataMap, boolean termDistance,
    Map<String, List<HitToken>> hitMap);
```

​		如代码中所示，dataMap参数即表示了一个文档。Tripod将文档看作是由多个部分组成的，如：文档包括题目、摘要、正文等。这些不同的部分的名字作为Map的key存在，而其对应的文字部分将被分词处理为有序词组后存放。由于开源的分词工具很多，所以Tripod并没有提供分词工具，而更多的希望Tripod作为整个NLP处理流程中的一环，能够复用分词后的序列，有效提升数据处理的效率。但是，对于分词后的对象要求其能够支持TermEntity接口，该接口如下：

```
/**
 * 词实体接口
 */
public interface TermEntity extends Serializable {
  // 词在文档中的序号，以词作为单位进行临近距离匹配时，依赖此属性
  int getIndex();
  // 词文本
  String getTerm();
  // 词在文档中的起始字符偏移，以字符为单位进行临近距离匹配时，依赖此属性
  int getOffset();
  // 词性(可选，计算时并不需要)
  String getPartOfSpeech();
}
```

​		TermEntity接口中给出了要实现的方法以及相关的解释，其中getIndex()和getOffset()两个方法的注释都提到了临近距离匹配，这个匹配即对应lucene语法的Proximity Searches查询。代码片段（1）match方法的第二个参数termDistance就是用于标示做临近距离匹配时以何为单位做距离计算的。如临近距离表达式为
”中国 发展”~2。当该值为true时，表示以词为单位进行距离计算，那么“中国 进入 飞速 发展 时期。”这句话就可以被命中，因为在“中国”和“发展”之间有两个词，满足表达式距离为2的要求；而当该值为false时，表示以字符为单位进行距离计算，那么“中国 进入 飞速 发展 时期。”这句话不符合表达式的匹配要求。因为在“中国”和“发展”两个词之间共有4个字符，不满足距离为2的要求。接口之所以设计这个参数，主要是考虑到分词技术的局限问题。如果不能很好的处理未登录词，或者进行过了停止词处理，那么以字符为单位计算距离很难给出合适的距离范围。而当以词为单位进行距离计算时，就会相对容易一些。

​		代码片段（1）match方法的第三个参数为一个[in/out]参数，该参数用于输出所有匹配命中的文本标记。如注释所说，该标记可以是词、短语或一段文本，当有临近距离匹配时，由于文本进行过了分词处理，有些文本内容可能已被舍弃，故无法还原原始的文本标记，故返回时只能返回命中文本标记的起止字符偏移了。该参数的返回值可用于高亮显示以及匹配规则调试。返回这些信息有一定的计算代价，应用时可根据需要填充是否要返回。

​		match方法的返回值为一个双精度浮点值。该值为lucene规则与文档的匹配相关度。关于相关度计算，可参见[lucene的评分机制](https://www.cnblogs.com/yjf512/p/4860134.html)。在进行评分时，由于要用到IDF值，该值的计算需要依赖词的通用程度，即词在多少个文档中出现过。这个值需要对所有处理过的数据进行累积，不能随着应用的终止而释放，并在下次计算时重新累积。每次都重新累积时，无法获得准确的评估值。为此，Tripod设计了IdfCounter接口，接口如下：

```
public interface IdfCounter {
  /**
   * 对集合中的词进行记数
   * @param terms 词集合，一般认为一个set集合为一篇文档中出现的所有去重后的词
   */
  void count(Set<String> terms);
  /**
   * 获得指定词的idf值
   * @param term 词
   * @return idf分值
   */
  double idf(String term);
  int getTotalDocs();
  Map<String, Integer> getTermDocs();
}
```

并给出了一个缺省实现，IdfCounterImpl，该实现拥有save()和load()的方法，但何时save，何时load交由使用者根据需要调用。

​       需要注意的是，Tripod采用的相关度打分原理与lucene一致，但很难保证打分值一致。但分值所表现的相关度强弱关系是一致的。另外，Tripod提供了一个是否进行分值评估的开关选项，setScoring()方法。在对实时文本匹配处理时，若只需要知道是否命中，而不需知道相关度时，可将打分机制关闭，即设置该值为false，则可以优化匹配效率。

​       Tripod对外提供服务的类主要有以下三个：

Ø  Tripod类

​		一个lucene语法描述的匹配规则会被构建为一个Tripod对象，该对象为一个lucene匹配规则的解释执行器。通过该对象的match方法，传入待处理的文档数据可返回文档与规则是否匹配以及匹配程度。

Ø  TripodEngine类

​		该对象可以被视为一个Tripod的集合。在实际应用场景中，一般不会只有一个匹配规则。往往会有多个匹配规则需要对文档进行同时匹配。如向不同客户推送数据的应用场景，或者依据特征规则进行文档分类的应用场景等。

Ø  TripodEvaluator类

​		该对象给出了一种计算lucene规则复杂度的实现。该复杂度的计算值无法精准体现计算资源的耗用情况，只能反映占用趋势。使用者可通过调节相应的计算系数，获得适用自己环境的复杂度评估值。在用户可以自定义数据匹配规则的应用场景里，可以通过设定复杂度阀值来约束用户，防止其设置的规则过于复杂，导致计算资源占用过大。

## Tripod使用

​	Tripod使用非常简单，其主要功能都集中在tripod-engine模块中，可通过如下maven依赖引入tripod-engine模块。

```
<dependency>
<groupId>org.datayoo.tripod</groupId>
    <artifactId>tripod-engine</artifactId>
    <version>1.0.0</version>
</dependency>
```

​	Tripod工程对每个主要服务类都有相关的测试示例代码。以下是org.datayoo.tripod.engine.TripodEngineTest的一段示例代码：

```
public void test1() {
  // 创建TripodEngine
  TripodEngine tripodEngine = createTripodEngine();
  // 构造测试数据文档，一个文档由多个部分，如：题目，内容；而每个部分由一堆词组成
  Map<String, TermEntity[]> dataMap = TripodTestHelper.createDataMap();
  // 匹配文档
  tripodEngine.match(dataMap, true);
  try {
    Thread.sleep(1000);
  } catch (InterruptedException e) {
    e.printStackTrace();
  }
}

protected static TripodEngine createTripodEngine() {
  // 描述文档的组成结构，及结构中每一部分的权重
  List<FieldMetadata> fieldMetadatas = new LinkedList<FieldMetadata>();
  FieldMetadata fieldMetadata = new FieldMetadata("title", 2);
  fieldMetadatas.add(fieldMetadata);
  fieldMetadata = new FieldMetadata("content", 1);
  fieldMetadatas.add(fieldMetadata);
  /*
  * 初始化TripodEngine，传入待处理的文档对象的字段信息，缺省字段及Idf计算辅助接口
  * IdfCounterImpl记录了文档与词的相关关系，可持久化该类的信息，每次使用Tripod时
  * 注入这些持久化数据，使Tripod的相关度计算结果尽量保持与lucene一致
  * */
  TripodEngine tripodEngine = new TripodEngine(fieldMetadatas, fieldMetadata,
      new IdfCounterImpl());
  // 设置引擎在匹配时计算相关度
  tripodEngine.setScoring(true);
  // 文档匹配监听器，当规则匹配文档后，通过该接口回调传回匹配结果
  TripodListener tripodListener = new TripodPrintListener();
  //    tripodEngine
  //        .addTripodRule("test", "(中办&title:中办)^2 任命 形式主义", tripodListener);
  // 向引擎添加基于lucene语法的过滤规则
  tripodEngine.addTripodRule("test1", "\"第5代 领导\" 任命 形式主义", tripodListener);
  return tripodEngine;
}
```

示例代码的执行结果为：

test1 : 0.066580

## Tripod语法

​       Tripod的语法与lucene的查询语法基本一致，只是稍做了调整。关于lucene查询语法这里不做赘述，可参见[lucene查询语法](https://lucene.apache.org/core/2_9_4/queryparsersyntax.html)了解详情。本部分只简单描述二者之间的差异。

​       Tripod语法中舍去了所有由字母组成的运算符，一律采用符号做为运算符，主要为了防止这些字母运算符作为词出现在文本中时，为使用带来不必要的混乱。下表给出二者间的差异：

| **Lucene查询语法**                   | **Tripod语法**                      | **说明**                                         |
| ------------------------------------ | ----------------------------------- | ------------------------------------------------ |
| mod_date:[20020101 TO 20030101]      | mod_date:[20020101,20030101]        | 区域运算符中的“TO”运算符在Tripod中用”,”替代      |
| "jakarta apache" OR "Apache Lucene"  | "jakarta apache" \| "Apache Lucene" | 逻辑或运算符“OR”在Tripod中用”\|”或者”\|\|”替代。 |
| "jakarta apache" AND "Apache Lucene" | "jakarta apache" & "Apache Lucene"  | 逻辑与运算符“AND”在Tripod中用”&”或者”&&”替代。   |
| NOT "jakarta apache"                 | ! "jakarta apache"                  | 逻辑非运算符“NOT”在Tripod中用”!”替代。           |

​		另外，Tripod中还有一个增强语法，“in语法”，其与 sql语言中的in运算符类似。其表达式如下：

​		***title:[****愤怒** **被喷** **垃圾** **碾压]****

​		其运算符与区域运算符类似，只是“[]”内的值使用“空格”分隔，且可以排列任意多个。“[]”内的值可以是词和短语。表达式在匹配时，若有任何一个值匹配中对应文档字段中的内容，即表示匹配成功。In语法可以用于有类似笛卡尔积计算的场景，如下应用场景：

​		***[北京 上海 广州] & [腾讯 阿里 百度]***

该规则表示文档的内容中至少要包括“北京”、“上海”、“广州”中的一个词以及“腾讯”、“阿里”、“百度”中的一个词，规则才能被命中。该逻辑lucene语法也能表达，只是表达起来较为繁琐。

