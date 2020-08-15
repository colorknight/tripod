package org.datayoo.tripod.metadata;

import org.apache.commons.lang3.Validate;

public class PhraseMetadata extends AbstractExpressionMetadata {

  protected char enclosure;

  protected String phrase;

  public PhraseMetadata(String phrase) {
    super(ExpressionType.PHRASE);
    Validate.notEmpty(phrase, "phrase is empty!");
    enclosure = phrase.charAt(0);
    this.phrase = phrase.substring(1, phrase.length() - 1);
  }

  public char getEnclosure() {
    return enclosure;
  }

  public String getPhrase() {
    return phrase;
  }

  public String getOriginalPhrase() {
    return String.format("%c%s%c", enclosure, phrase, enclosure);
  }

  public String toString() {
    if (boost == 1)
      return getOriginalPhrase();
    else {
      return String.format("%s^%d", getOriginalPhrase(), boost);
    }
  }
}
