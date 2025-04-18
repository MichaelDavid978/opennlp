/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package opennlp.tools.formats.brat;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import opennlp.tools.namefind.NameSample;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.ObjectStream;

/**
 * Generates Name Sample objects for a Brat Document object.
 */
public class BratNameSampleStream extends SegmenterObjectStream<BratDocument, NameSample> {

  private final BratDocumentParser parser;

  /**
   * Initializes a new {@link BratNameSampleStream} with the specified parameters
   * based on a straem of {@link BratDocument documents}.
   *
   * @param sentDetector A valid {@link SentenceDetector} instance.
   * @param tokenizer A valid {@link Tokenizer} instance.
   * @param samples The {@link BratDocument} {@link ObjectStream} to process.
   */
  public BratNameSampleStream(SentenceDetector sentDetector,
                              Tokenizer tokenizer, ObjectStream<BratDocument> samples) {
    this(sentDetector, tokenizer, samples, null);
  }

  /**
   * Initializes a new {@link BratNameSampleStream} with the specified (model) parameters.
   *
   * @param sentModel A valid {@link SentenceModel sentence detection model}.
   * @param tokenModel A valid {@link TokenizerModel tokenizer model}.
   * @param samples The {@link BratDocument} {@link ObjectStream} to process.
   */
  public BratNameSampleStream(SentenceModel sentModel, TokenizerModel tokenModel,
                              ObjectStream<BratDocument> samples) {
    this(new SentenceDetectorME(sentModel), new TokenizerME(tokenModel), samples, null);
  }

  /**
   * Creates a new {@link BratNameSampleStream}.
   * @param sentDetector A valid {@link SentenceDetector} instance.
   * @param tokenizer A valid {@link Tokenizer} instance.
   * @param samples The {@link BratDocument} {@link ObjectStream} to process.
   *                
   * @param nameTypes the name types to use or {@code null} if all name types.
   */
  public BratNameSampleStream(SentenceDetector sentDetector,
      Tokenizer tokenizer, ObjectStream<BratDocument> samples, Set<String> nameTypes) {
    super(samples);
    this.parser = new BratDocumentParser(sentDetector, tokenizer, nameTypes);
  }

  /**
   * Creates a new {@link BratNameSampleStream}.
   * @param sentModel a {@link SentenceModel} model
   * @param tokenModel a {@link TokenizerModel} model
   * @param samples a {@link BratDocument} {@link ObjectStream}
   * @param nameTypes the name types to use or {@code null} if all name types
   */
  public BratNameSampleStream(SentenceModel sentModel, TokenizerModel tokenModel,
      ObjectStream<BratDocument> samples, Set<String> nameTypes) {
    super(samples);
    // Hint: We can pass in custom validators here ...
    this.parser = new BratDocumentParser(new SentenceDetectorME(sentModel),
        new TokenizerME(tokenModel), nameTypes);
  }

  @Override
  protected List<NameSample> read(BratDocument sample) throws IOException {
    return parser.parse(sample);
  }
}
