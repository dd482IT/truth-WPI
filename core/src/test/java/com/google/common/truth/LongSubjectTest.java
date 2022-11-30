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

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for Long Subjects.
 *
 * @author David Saff
 * @author Christian Gruber
 * @author Kurt Alfred Kluever
 */
public class LongSubjectTest extends BaseSubjectTestCase {

  public void simpleEquality() {
    assertThat(4L).isEqualTo(4L);
  }

  public void simpleInequality() {
    assertThat(4L).isNotEqualTo(5L);
  }

  public void equalityWithInts() {
    assertThat(0L).isEqualTo(0);
    expectFailureWhenTestingThat(0L).isNotEqualTo(0);
  }

  public void equalityFail() {
    expectFailureWhenTestingThat(4L).isEqualTo(5L);
  }

  public void inequalityFail() {
    expectFailureWhenTestingThat(4L).isNotEqualTo(4L);
  }

  public void equalityOfNulls() {
    assertThat((Long) null).isEqualTo(null);
  }

  public void equalityOfNullsFail_nullActual() {
    expectFailureWhenTestingThat(null).isEqualTo(5L);
  }

  public void equalityOfNullsFail_nullExpected() {
    expectFailureWhenTestingThat(5L).isEqualTo(null);
  }

  public void inequalityOfNulls() {
    assertThat(4L).isNotEqualTo(null);
    assertThat((Integer) null).isNotEqualTo(4L);
  }

  public void inequalityOfNullsFail() {
    expectFailureWhenTestingThat(null).isNotEqualTo(null);
  }

  public void testNumericTypeWithSameValue_shouldBeEqual_long_long() {
    expectFailureWhenTestingThat(42L).isNotEqualTo(42L);
  }

  public void testNumericTypeWithSameValue_shouldBeEqual_long_int() {
    expectFailureWhenTestingThat(42L).isNotEqualTo(42);
  }

  public void isGreaterThan_int_strictly() {
    expectFailureWhenTestingThat(2L).isGreaterThan(3);
  }

  public void isGreaterThan_int() {
    expectFailureWhenTestingThat(2L).isGreaterThan(2);
    assertThat(2L).isGreaterThan(1);
  }

  public void isLessThan_int_strictly() {
    expectFailureWhenTestingThat(2L).isLessThan(1);
  }

  public void isLessThan_int() {
    expectFailureWhenTestingThat(2L).isLessThan(2);
    assertThat(2L).isLessThan(3);
  }

  public void isAtLeast_int() {
    expectFailureWhenTestingThat(2L).isAtLeast(3);
    assertThat(2L).isAtLeast(2);
    assertThat(2L).isAtLeast(1);
  }

  public void isAtMost_int() {
    expectFailureWhenTestingThat(2L).isAtMost(1);
    assertThat(2L).isAtMost(2);
    assertThat(2L).isAtMost(3);
  }

  private LongSubject expectFailureWhenTestingThat(Long actual) {
    return expectFailure.whenTesting().that(actual);
  }
}
