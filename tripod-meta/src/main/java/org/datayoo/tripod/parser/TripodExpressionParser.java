/*
 * Copyright 2020 Taiding Tang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.datayoo.tripod.parser;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.lang3.Validate;
import org.datayoo.tripod.antlr.TripodLexer;
import org.datayoo.tripod.antlr.TripodParser;
import org.datayoo.tripod.exception.TripodRuntimeException;
import org.datayoo.tripod.metadata.BoolMetadata;

import java.io.InputStream;
import java.io.Reader;

public class TripodExpressionParser {

  public static BoolMetadata parseFromFile(String fileName) {
    Validate.notEmpty(fileName, "fileName is empty!");
    try {
      CharStream charStream = CharStreams.fromFileName(fileName);
      TripodLexer tripodLexer = new TripodLexer(charStream);
      CommonTokenStream tokens = new CommonTokenStream(tripodLexer);
      TripodParser tripodParser = new TripodParser(tokens);
      tripodParser.setErrorHandler(new BailErrorStrategy());
      TripodParser.TripodExpressionContext context = tripodParser
          .tripodExpression();
      if (context.exception != null) {
        throw new TripodRuntimeException(context.exception.getMessage(),
            context.exception);
      }
      if (!tripodLexer._hitEOF) {
        throw new TripodRuntimeException("Unsupported grammar!");
      }
      return context.boolMetadata;
    } catch (Throwable t) {
      throw new TripodRuntimeException("Invalid expression!", t);
    }
  }

  public static BoolMetadata parseFromStream(InputStream inputStream) {
    Validate.notNull(inputStream, "inputStream is null!");
    try {
      CharStream charStream = CharStreams.fromStream(inputStream);
      TripodLexer tripodLexer = new TripodLexer(charStream);
      CommonTokenStream tokens = new CommonTokenStream(tripodLexer);
      TripodParser tripodParser = new TripodParser(tokens);
      tripodParser.setErrorHandler(new BailErrorStrategy());
      TripodParser.TripodExpressionContext context = tripodParser
          .tripodExpression();
      if (context.exception != null) {
        throw new TripodRuntimeException(context.exception.getMessage(),
            context.exception);
      }
      if (!tripodLexer._hitEOF) {
        throw new TripodRuntimeException("Unsupported grammar!");
      }
      return context.boolMetadata;
    } catch (Throwable t) {
      throw new TripodRuntimeException("Invalid expression!", t);
    }
  }

  public static BoolMetadata parseFromReader(Reader reader) {
    Validate.notNull(reader, "reader is null!");
    try {
      CharStream charStream = CharStreams.fromReader(reader);
      TripodLexer tripodLexer = new TripodLexer(charStream);
      CommonTokenStream tokens = new CommonTokenStream(tripodLexer);
      TripodParser tripodParser = new TripodParser(tokens);
      tripodParser.setErrorHandler(new BailErrorStrategy());
      TripodParser.TripodExpressionContext context = tripodParser
          .tripodExpression();
      if (context.exception != null) {
        throw new TripodRuntimeException(context.exception.getMessage(),
            context.exception);
      }
      if (!tripodLexer._hitEOF) {
        throw new TripodRuntimeException("Unsupported grammar!");
      }
      return context.boolMetadata;
    } catch (Throwable t) {
      throw new TripodRuntimeException("Invalid expression!", t);
    }
  }

  public static BoolMetadata parseFromString(String data) {
    Validate.notEmpty(data, "data is empty!");
    try {
      CharStream charStream = CharStreams.fromString(data);
      TripodLexer tripodLexer = new TripodLexer(charStream);
      CommonTokenStream tokens = new CommonTokenStream(tripodLexer);
      TripodParser tripodParser = new TripodParser(tokens);
      tripodParser.setErrorHandler(new BailErrorStrategy());
      TripodParser.TripodExpressionContext context = tripodParser
          .tripodExpression();
      if (context.exception != null) {
        throw new TripodRuntimeException(context.exception.getMessage(),
            context.exception);
      }
      if (!tripodLexer._hitEOF) {
        throw new TripodRuntimeException("Unsupported grammar!");
      }
      return context.boolMetadata;
    } catch (Throwable t) {
      throw new TripodRuntimeException(
          String.format("Invalid expression '%s'", data), t);
    }
  }
}
