/*
 * Copyright (c) 2011 Google, Inc.
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

import static com.google.common.truth.ExpectFailure.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

import com.google.common.annotations.GwtIncompatible;
import java.util.regex.Pattern;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for String Subjects.
 *
 * @author David Saff
 * @author Christian Gruber (cgruber@israfil.net)
 */
public class StringSubjectTest extends BaseSubjectTestCase {

  public void hasLength() {
    assertThat("kurt").hasLength(4);
  }

  public void hasLengthZero() {
    assertThat("").hasLength(0);
  }

  public void hasLengthFails() {
    expectFailureWhenTestingThat("kurt").hasLength(5);
    assertFailureValue("value of", "string.length()");
  }

  public void hasLengthNegative() {
    try {
      assertThat("kurt").hasLength(-1);
      fail();
    } catch (IllegalArgumentException expected) {
    }
  }

  public void stringIsEmpty() {
    assertThat("").isEmpty();
  }

  public void stringIsEmptyFail() {
    expectFailureWhenTestingThat("abc").isEmpty();
    assertFailureKeys("expected to be empty", "but was");
  }

  public void stringIsEmptyFailNull() {
    expectFailureWhenTestingThat(null).isEmpty();
    assertFailureKeys("expected an empty string", "but was");
  }

  public void stringIsNotEmpty() {
    assertThat("abc").isNotEmpty();
  }

  public void stringIsNotEmptyFail() {
    expectFailureWhenTestingThat("").isNotEmpty();
    assertFailureKeys("expected not to be empty");
  }

  public void stringIsNotEmptyFailNull() {
    expectFailureWhenTestingThat(null).isNotEmpty();
    assertFailureKeys("expected a non-empty string", "but was");
  }

  public void stringContains() {
    assertThat("abc").contains("c");
  }

  public void stringContainsCharSeq() {
    CharSequence charSeq = new StringBuilder("c");
    assertThat("abc").contains(charSeq);
  }

  public void stringContainsFail() {
    expectFailureWhenTestingThat("abc").contains("d");
    assertFailureValue("expected to contain", "d");
  }

  public void stringDoesNotContain() {
    assertThat("abc").doesNotContain("d");
  }

  public void stringDoesNotContainCharSequence() {
    CharSequence charSeq = new StringBuilder("d");
    assertThat("abc").doesNotContain(charSeq);
  }

  public void stringDoesNotContainFail() {
    expectFailureWhenTestingThat("abc").doesNotContain("b");
    assertFailureValue("expected not to contain", "b");
  }

  public void stringEquality() {
    assertThat("abc").isEqualTo("abc");
  }

  public void stringEqualityToNull() {
    expectFailureWhenTestingThat("abc").isEqualTo(null);
    assertThat(expectFailure.getFailure()).isNotInstanceOf(ComparisonFailureWithFacts.class);
  }

  public void stringEqualityToEmpty() {
    expectFailureWhenTestingThat("abc").isEqualTo("");
    assertFailureKeys("expected an empty string", "but was");
  }

  public void stringEqualityEmptyToNonEmpty() {
    expectFailureWhenTestingThat("").isEqualTo("abc");
    assertFailureKeys("expected", "but was an empty string");
  }

  public void stringEqualityFail() {
    expectFailureWhenTestingThat("abc").isEqualTo("ABC");
    assertThat(expectFailure.getFailure()).isInstanceOf(ComparisonFailureWithFacts.class);
  }

  public void stringStartsWith() {
    assertThat("abc").startsWith("ab");
  }

  public void stringStartsWithFail() {
    expectFailureWhenTestingThat("abc").startsWith("bc");
    assertFailureValue("expected to start with", "bc");
  }

  public void stringEndsWith() {
    assertThat("abc").endsWith("bc");
  }

  public void stringEndsWithFail() {
    expectFailureWhenTestingThat("abc").endsWith("ab");
    assertFailureValue("expected to end with", "ab");
  }

  public void emptyStringTests() {
    assertThat("").contains("");
    assertThat("").startsWith("");
    assertThat("").endsWith("");
    assertThat("a").contains("");
    assertThat("a").startsWith("");
    assertThat("a").endsWith("");
  }

  public void stringMatchesString() {
    assertThat("abcaaadev").matches(".*aaa.*");
  }

  public void stringMatchesStringWithFail() {
    expectFailureWhenTestingThat("abcaqadev").matches(".*aaa.*");
    assertFailureValue("expected to match", ".*aaa.*");
  }

  public void stringMatchesStringFailNull() {
    expectFailureWhenTestingThat(null).matches(".*aaa.*");
    assertFailureValue("expected a string that matches", ".*aaa.*");
  }

  public void stringMatchesStringLiteralFail() {
    expectFailureWhenTestingThat("$abc").matches("$abc");
    assertFailureValue("expected to match", "$abc");
    assertFailureValue("but was", "$abc");
    assertThat(expectFailure.getFailure())
        .factKeys()
        .contains("Looks like you want to use .isEqualTo() for an exact equality assertion.");
  }

  public void stringMatchesPattern() {
    assertThat("abcaaadev").matches(Pattern.compile(".*aaa.*"));
  }

  public void stringMatchesPatternWithFail() {
    expectFailureWhenTestingThat("abcaqadev").matches(Pattern.compile(".*aaa.*"));
    assertFailureValue("expected to match", ".*aaa.*");
  }

  public void stringMatchesPatternFailNull() {
    expectFailureWhenTestingThat(null).matches(Pattern.compile(".*aaa.*"));
    assertFailureValue("expected a string that matches", ".*aaa.*");
  }

  public void stringMatchesPatternLiteralFail() {
    expectFailureWhenTestingThat("$abc").matches(Pattern.compile("$abc"));
    assertFailureValue("expected to match", "$abc");
    assertFailureValue("but was", "$abc");
    assertThat(expectFailure.getFailure())
        .factKeys()
        .contains(
            "If you want an exact equality assertion you can escape your regex with"
                + " Pattern.quote().");
  }

  public void stringDoesNotMatchString() {
    assertThat("abcaqadev").doesNotMatch(".*aaa.*");
  }

  public void stringDoesNotMatchStringWithFail() {
    expectFailureWhenTestingThat("abcaaadev").doesNotMatch(".*aaa.*");
    assertFailureValue("expected not to match", ".*aaa.*");
  }

  public void stringDoesNotMatchStringFailNull() {
    expectFailureWhenTestingThat(null).doesNotMatch(".*aaa.*");
    assertFailureValue("expected a string that does not match", ".*aaa.*");
  }

  public void stringDoesNotMatchPattern() {
    assertThat("abcaqadev").doesNotMatch(Pattern.compile(".*aaa.*"));
  }

  public void stringDoesNotMatchPatternWithFail() {
    expectFailureWhenTestingThat("abcaaadev").doesNotMatch(Pattern.compile(".*aaa.*"));
    assertFailureValue("expected not to match", ".*aaa.*");
  }

  public void stringDoesNotMatchPatternFailNull() {
    expectFailureWhenTestingThat(null).doesNotMatch(Pattern.compile(".*aaa.*"));
    assertFailureValue("expected a string that does not match", ".*aaa.*");
  }

  public void stringContainsMatchStringUsesFind() {
    assertThat("aba").containsMatch("[b]");
    assertThat("aba").containsMatch(Pattern.compile("[b]"));
  }

  public void stringContainsMatchString() {
    assertThat("aba").containsMatch(".*b.*");

    expectFailureWhenTestingThat("aaa").containsMatch(".*b.*");
    assertFailureValue("expected to contain a match for", ".*b.*");
  }

  public void stringContainsMatchStringFailNull() {
    expectFailureWhenTestingThat(null).containsMatch(".*b.*");
    assertFailureValue("expected a string that contains a match for", ".*b.*");
  }

  public void stringContainsMatchPattern() {
    assertThat("aba").containsMatch(Pattern.compile(".*b.*"));

    expectFailureWhenTestingThat("aaa").containsMatch(Pattern.compile(".*b.*"));
    assertFailureValue("expected to contain a match for", ".*b.*");
  }

  public void stringContainsMatchPatternFailNull() {
    expectFailureWhenTestingThat(null).containsMatch(Pattern.compile(".*b.*"));
    assertFailureValue("expected a string that contains a match for", ".*b.*");
  }

  public void stringDoesNotContainMatchString() {
    assertThat("aaa").doesNotContainMatch(".*b.*");

    expectFailureWhenTestingThat("aba").doesNotContainMatch(".*b.*");
    assertFailureValue("expected not to contain a match for", ".*b.*");
  }

  public void stringDoesNotContainMatchStringUsesFind() {
    expectFailureWhenTestingThat("aba").doesNotContainMatch("[b]");
    assertFailureValue("expected not to contain a match for", "[b]");
  }

  public void stringDoesNotContainMatchStringUsesFindFailNull() {
    expectFailureWhenTestingThat(null).doesNotContainMatch("[b]");
    assertFailureValue("expected a string that does not contain a match for", "[b]");
  }

  public void stringDoesNotContainMatchPattern() {
    assertThat("zzaaazz").doesNotContainMatch(Pattern.compile(".b."));

    expectFailureWhenTestingThat("zzabazz").doesNotContainMatch(Pattern.compile(".b."));
    assertFailureValue("expected not to contain a match for", ".b.");
    assertFailureValue("but contained", "aba");
    assertFailureValue("full string", "zzabazz");
  }

  public void stringDoesNotContainMatchPatternFailNull() {
    expectFailureWhenTestingThat(null).doesNotContainMatch(Pattern.compile(".b."));
    assertFailureValue("expected a string that does not contain a match for", ".b.");
  }

  public void stringEqualityIgnoringCase() {
    assertThat("café").ignoringCase().isEqualTo("CAFÉ");
  }

  public void stringEqualityIgnoringCaseWithNullSubject() {
    assertThat((String) null).ignoringCase().isEqualTo(null);
  }

  public void stringEqualityIgnoringCaseFail() {
    expectFailureWhenTestingThat("abc").ignoringCase().isEqualTo("abd");

    assertFailureValue("expected", "abd");
    assertThat(expectFailure.getFailure()).factKeys().contains("(case is ignored)");
  }

  public void stringEqualityIgnoringCaseFailWithNullSubject() {
    expectFailureWhenTestingThat((String) null).ignoringCase().isEqualTo("abc");

    assertFailureValue("expected a string that is equal to", "abc");
    assertThat(expectFailure.getFailure()).factKeys().contains("(case is ignored)");
  }

  public void stringEqualityIgnoringCaseFailWithNullExpectedString() {
    expectFailureWhenTestingThat("abc").ignoringCase().isEqualTo(null);

    assertFailureValue("expected", "null (null reference)");
    assertThat(expectFailure.getFailure()).factKeys().contains("(case is ignored)");
  }

  public void stringInequalityIgnoringCase() {
    assertThat("café").ignoringCase().isNotEqualTo("AFÉ");
  }

  public void stringInequalityIgnoringCaseWithNullSubject() {
    assertThat((String) null).ignoringCase().isNotEqualTo("abc");
  }

  public void stringInequalityIgnoringCaseWithNullExpectedString() {
    assertThat("abc").ignoringCase().isNotEqualTo(null);
  }

  public void stringInequalityIgnoringCaseFail() {
    expectFailureWhenTestingThat("café").ignoringCase().isNotEqualTo("CAFÉ");

    assertFailureValue("expected not to be", "CAFÉ");
    assertThat(expectFailure.getFailure()).factKeys().contains("(case is ignored)");
  }

  public void stringInequalityIgnoringCaseFailWithNullSubject() {
    expectFailureWhenTestingThat((String) null).ignoringCase().isNotEqualTo(null);

    assertFailureValue("expected a string that is not equal to", "null (null reference)");
    assertThat(expectFailure.getFailure()).factKeys().contains("(case is ignored)");
  }

  public void stringContainsIgnoringCase() {
    assertThat("äbc").ignoringCase().contains("Ä");
  }

  public void stringContainsIgnoringCaseEmptyString() {
    assertThat("abc").ignoringCase().contains("");
  }

  public void stringContainsIgnoringCaseWithWord() {
    assertThat("abcdé").ignoringCase().contains("CdÉ");
  }

  public void stringContainsIgnoringCaseWholeWord() {
    assertThat("abcde").ignoringCase().contains("ABCde");
  }

  public void stringContainsIgnoringCaseCharSeq() {
    CharSequence charSeq = new StringBuilder("C");
    assertThat("abc").ignoringCase().contains(charSeq);
  }

  public void stringContainsIgnoringCaseFail() {
    expectFailureWhenTestingThat("abc").ignoringCase().contains("d");

    assertFailureValue("expected to contain", "d");
    assertThat(expectFailure.getFailure()).factKeys().contains("(case is ignored)");
  }

  public void stringContainsIgnoringCaseFailBecauseTooLarge() {
    expectFailureWhenTestingThat("abc").ignoringCase().contains("abcc");

    assertFailureValue("expected to contain", "abcc");
    assertThat(expectFailure.getFailure()).factKeys().contains("(case is ignored)");
  }

  public void stringContainsIgnoringCaseFailBecauseNullSubject() {
    expectFailureWhenTestingThat((String) null).ignoringCase().contains("d");

    assertFailureValue("expected a string that contains", "d");
    assertThat(expectFailure.getFailure()).factKeys().contains("(case is ignored)");
  }

  public void stringDoesNotContainIgnoringCase() {
    assertThat("äbc").ignoringCase().doesNotContain("Äc");
  }

  public void stringDoesNotContainIgnoringCaseCharSeq() {
    CharSequence charSeq = new StringBuilder("cb");
    assertThat("abc").ignoringCase().doesNotContain(charSeq);
  }

  public void stringDoesNotContainIgnoringCaseFail() {
    expectFailureWhenTestingThat("äbc").ignoringCase().doesNotContain("Äb");

    assertFailureValue("expected not to contain", "Äb");
    assertThat(expectFailure.getFailure()).factKeys().contains("(case is ignored)");
  }

  public void stringDoesNotContainIgnoringCaseFailWithEmptyString() {
    expectFailureWhenTestingThat("abc").ignoringCase().doesNotContain("");

    assertFailureValue("expected not to contain", "");
    assertThat(expectFailure.getFailure()).factKeys().contains("(case is ignored)");
  }

  public void stringDoesNotContainIgnoringCaseFailBecauseNullSubject() {
    expectFailureWhenTestingThat((String) null).ignoringCase().doesNotContain("d");

    assertFailureValue("expected a string that does not contain", "d");
    assertThat(expectFailure.getFailure()).factKeys().contains("(case is ignored)");
  }

  public void trailingWhitespaceInActual() {
    expectFailureWhenTestingThat("foo\n").isEqualTo("foo");
    assertFailureKeys("expected", "but contained extra trailing whitespace");
    assertFailureValue("but contained extra trailing whitespace", "\\n");
  }

  public void trailingWhitespaceInExpected() {
    expectFailureWhenTestingThat("foo").isEqualTo("foo ");
    assertFailureKeys("expected", "but was missing trailing whitespace");
    assertFailureValue("but was missing trailing whitespace", "␣");
  }

  public void trailingWhitespaceInBoth() {
    expectFailureWhenTestingThat("foo \n").isEqualTo("foo\u00a0");
    assertFailureKeys("expected", "with trailing whitespace", "but trailing whitespace was");
    assertFailureValue("with trailing whitespace", "\\u00a0");
    assertFailureValue("but trailing whitespace was", "␣\\n");
  }

  public void trailingWhitespaceVsEmptyString() {
    /*
     * The code has special cases for both trailing whitespace and an empty string. Make sure that
     * it specifically reports the trailing whitespace. (It might be nice to *also* report the empty
     * string specially, but that's less important.)
     */
    expectFailureWhenTestingThat("\t").isEqualTo("");
    assertFailureKeys("expected", "but contained extra trailing whitespace");
    assertFailureValue("but contained extra trailing whitespace", "\\t");
  }

  private StringSubject expectFailureWhenTestingThat(String actual) {
    return expectFailure.whenTesting().that(actual);
  }
}
