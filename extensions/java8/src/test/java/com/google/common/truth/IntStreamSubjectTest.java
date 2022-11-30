/*
 * Copyright (c) 2016 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.common.truth;

import static com.google.common.truth.FailureAssertions.assertFailureKeys;
import static com.google.common.truth.FailureAssertions.assertFailureValue;
import static com.google.common.truth.IntStreamSubject.intStreams;
import static com.google.common.truth.Truth8.assertThat;
import static java.util.Arrays.asList;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for Java 8 {@link IntStream} Subjects.
 *
 * @author Kurt Alfred Kluever
 */
public final class IntStreamSubjectTest {

  public void testIsEqualTo() throws Exception {
    IntStream stream = IntStream.of(42);
    assertThat(stream).isEqualTo(stream);
  }

  public void testIsEqualToList() throws Exception {
    IntStream stream = IntStream.of(42);
    List<Integer> list = asList(42);
    AssertionError unused = expectFailure(whenTesting -> whenTesting.that(stream).isEqualTo(list));
  }

  public void testNullStream_fails() throws Exception {
    IntStream nullStream = null;
    try {
      assertThat(nullStream).isEmpty();
      fail();
    } catch (NullPointerException expected) {
    }
  }

  public void testNullStreamIsNull() throws Exception {
    IntStream nullStream = null;
    assertThat(nullStream).isNull();
  }

  public void testIsSameInstanceAs() throws Exception {
    IntStream stream = IntStream.of(1);
    assertThat(stream).isSameInstanceAs(stream);
  }

  public void testIsEmpty() throws Exception {
    assertThat(IntStream.of()).isEmpty();
  }

  public void testIsEmpty_fails() throws Exception {
    AssertionError unused =
        expectFailure(whenTesting -> whenTesting.that(IntStream.of(42)).isEmpty());
  }

  public void testIsNotEmpty() throws Exception {
    assertThat(IntStream.of(42)).isNotEmpty();
  }

  public void testIsNotEmpty_fails() throws Exception {
    AssertionError unused =
        expectFailure(whenTesting -> whenTesting.that(IntStream.of()).isNotEmpty());
  }

  public void testHasSize() throws Exception {
    assertThat(IntStream.of(42)).hasSize(1);
  }

  public void testHasSize_fails() throws Exception {
    AssertionError unused =
        expectFailure(whenTesting -> whenTesting.that(IntStream.of(42)).hasSize(2));
  }

  public void testContainsNoDuplicates() throws Exception {
    assertThat(IntStream.of(42)).containsNoDuplicates();
  }

  public void testContainsNoDuplicates_fails() throws Exception {
    AssertionError unused =
        expectFailure(whenTesting -> whenTesting.that(IntStream.of(42, 42)).containsNoDuplicates());
  }

  public void testContains() throws Exception {
    assertThat(IntStream.of(42)).contains(42);
  }

  public void testContains_fails() throws Exception {
    AssertionError unused =
        expectFailure(whenTesting -> whenTesting.that(IntStream.of(42)).contains(100));
  }

  public void testContainsAnyOf() throws Exception {
    assertThat(IntStream.of(42)).containsAnyOf(42, 43);
  }

  public void testContainsAnyOf_fails() throws Exception {
    AssertionError unused =
        expectFailure(whenTesting -> whenTesting.that(IntStream.of(42)).containsAnyOf(43, 44));
  }

  public void testContainsAnyIn() throws Exception {
    assertThat(IntStream.of(42)).containsAnyIn(asList(42, 43));
  }

  public void testContainsAnyIn_fails() throws Exception {
    AssertionError unused =
        expectFailure(
            whenTesting -> whenTesting.that(IntStream.of(42)).containsAnyIn(asList(43, 44)));
  }

  public void testDoesNotContain() throws Exception {
    assertThat(IntStream.of(42)).doesNotContain(43);
  }

  public void testDoesNotContain_fails() throws Exception {
    AssertionError unused =
        expectFailure(whenTesting -> whenTesting.that(IntStream.of(42)).doesNotContain(42));
  }

  public void testContainsNoneOf() throws Exception {
    assertThat(IntStream.of(42)).containsNoneOf(43, 44);
  }

  public void testContainsNoneOf_fails() throws Exception {
    AssertionError unused =
        expectFailure(whenTesting -> whenTesting.that(IntStream.of(42)).containsNoneOf(42, 43));
  }

  public void testContainsNoneIn() throws Exception {
    assertThat(IntStream.of(42)).containsNoneIn(asList(43, 44));
  }

  public void testContainsNoneIn_fails() throws Exception {
    AssertionError unused =
        expectFailure(
            whenTesting -> whenTesting.that(IntStream.of(42)).containsNoneIn(asList(42, 43)));
  }

  public void testContainsAtLeast() throws Exception {
    assertThat(IntStream.of(42, 43)).containsAtLeast(42, 43);
  }

  public void testContainsAtLeast_fails() throws Exception {
    AssertionError unused =
        expectFailure(
            whenTesting -> whenTesting.that(IntStream.of(42, 43)).containsAtLeast(42, 43, 44));
  }

  public void testContainsAtLeast_inOrder() throws Exception {
    assertThat(IntStream.of(42, 43)).containsAtLeast(42, 43).inOrder();
  }

  public void testContainsAtLeast_inOrder_fails() throws Exception {
    try {
      assertThat(IntStream.of(42, 43)).containsAtLeast(43, 42).inOrder();
      fail();
    } catch (AssertionError expected) {
      assertFailureKeys(
          expected,
          "required elements were all found, but order was wrong",
          "expected order for required elements",
          "but was");
      assertFailureValue(expected, "expected order for required elements", "[43, 42]");
    }
  }

  public void testContainsAtLeastElementsIn() throws Exception {
    assertThat(IntStream.of(42, 43)).containsAtLeastElementsIn(asList(42, 43));
  }

  public void testContainsAtLeastElementsIn_fails() throws Exception {
    AssertionError unused =
        expectFailure(
            whenTesting ->
                whenTesting
                    .that(IntStream.of(42, 43))
                    .containsAtLeastElementsIn(asList(42, 43, 44)));
  }

  public void testContainsAtLeastElementsIn_inOrder() throws Exception {
    assertThat(IntStream.of(42, 43)).containsAtLeastElementsIn(asList(42, 43)).inOrder();
  }

  public void testContainsAtLeastElementsIn_inOrder_fails() throws Exception {
    try {
      assertThat(IntStream.of(42, 43)).containsAtLeastElementsIn(asList(43, 42)).inOrder();
      fail();
    } catch (AssertionError expected) {
      assertFailureKeys(
          expected,
          "required elements were all found, but order was wrong",
          "expected order for required elements",
          "but was");
      assertFailureValue(expected, "expected order for required elements", "[43, 42]");
    }
  }

  public void testContainsExactly() throws Exception {
    assertThat(IntStream.of(42, 43)).containsExactly(42, 43);
  }

  public void testContainsExactly_fails() throws Exception {
    try {
      assertThat(IntStream.of(42, 43)).containsExactly(42);
      fail();
    } catch (AssertionError expected) {
      assertFailureKeys(expected, "unexpected (1)", "---", "expected", "but was");
      assertFailureValue(expected, "expected", "[42]");
    }
  }

  public void testContainsExactly_inOrder() throws Exception {
    assertThat(IntStream.of(42, 43)).containsExactly(42, 43).inOrder();
  }

  public void testContainsExactly_inOrder_fails() throws Exception {
    try {
      assertThat(IntStream.of(42, 43)).containsExactly(43, 42).inOrder();
      fail();
    } catch (AssertionError expected) {
      assertFailureKeys(expected, "contents match, but order was wrong", "expected", "but was");
      assertFailureValue(expected, "expected", "[43, 42]");
    }
  }

  public void testContainsExactlyElementsIn() throws Exception {
    assertThat(IntStream.of(42, 43)).containsExactlyElementsIn(asList(42, 43));
    assertThat(IntStream.of(42, 43)).containsExactlyElementsIn(asList(43, 42));
  }

  public void testContainsExactlyElementsIn_fails() throws Exception {
    try {
      assertThat(IntStream.of(42, 43)).containsExactlyElementsIn(asList(42));
      fail();
    } catch (AssertionError expected) {
      assertFailureKeys(expected, "unexpected (1)", "---", "expected", "but was");
      assertFailureValue(expected, "expected", "[42]");
    }
  }

  public void testContainsExactlyElementsIn_inOrder() throws Exception {
    assertThat(IntStream.of(42, 43)).containsExactlyElementsIn(asList(42, 43)).inOrder();
  }

  public void testContainsExactlyElementsIn_inOrder_fails() throws Exception {
    try {
      assertThat(IntStream.of(42, 43)).containsExactlyElementsIn(asList(43, 42)).inOrder();
      fail();
    } catch (AssertionError expected) {
      assertFailureKeys(expected, "contents match, but order was wrong", "expected", "but was");
      assertFailureValue(expected, "expected", "[43, 42]");
    }
  }

  public void testContainsExactlyElementsIn_inOrder_intStream() throws Exception {
    assertThat(IntStream.of(1, 2, 3, 4)).containsExactly(1, 2, 3, 4).inOrder();
  }

  public void testIsInOrder() {
    assertThat(IntStream.of()).isInOrder();
    assertThat(IntStream.of(1)).isInOrder();
    assertThat(IntStream.of(1, 1, 2, 3, 3, 3, 4)).isInOrder();
  }

  public void testIsInOrder_fails() {
    AssertionError unused =
        expectFailure(whenTesting -> whenTesting.that(IntStream.of(1, 3, 2, 4)).isInOrder());
  }

  public void testIsInStrictOrder() {
    assertThat(IntStream.of()).isInStrictOrder();
    assertThat(IntStream.of(1)).isInStrictOrder();
    assertThat(IntStream.of(1, 2, 3, 4)).isInStrictOrder();
  }

  public void testIsInStrictOrder_fails() {
    AssertionError unused =
        expectFailure(whenTesting -> whenTesting.that(IntStream.of(1, 2, 2, 4)).isInStrictOrder());
  }

  private static AssertionError expectFailure(
      ExpectFailure.SimpleSubjectBuilderCallback<IntStreamSubject, IntStream> assertionCallback) {
    return ExpectFailure.expectFailureAbout(intStreams(), assertionCallback);
  }
}
