package org.datayoo.tripod.metadata;

public enum ExpressionType {

  SPACE_OR, // should

  OR,       // |

  AND,      // &

  NOT,      // !

  MUST,     // +

  MINUS,    // -

  PAREN,    // (t t)

  RANGE,    // [n,n]

  IN,       // [t t]

  FIELD,    // field

  FUZZY,    // ~ term

  PROXIMITY,// ~ phrase

  PHRASE,   // phrase

  TERM,     // term

  WILDCARD  // ? * etc

}
