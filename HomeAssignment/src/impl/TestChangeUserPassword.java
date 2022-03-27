package impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestChangeUserPassword {

	ChangeUserPassword testObj = new ChangeUserPassword();

	// New Password cannot be same as Old Password
	@Test
	void test0() {
		assertEquals(false, testObj.changePassword("Ashish@*12345abcdefghi", "Ashish@*12345abcdefghi"));
	}

	// At least 18 alphanumeric characters Negative, char count<18
	@Test
	void test1() {
		assertEquals(false, testObj.changePassword("Ashish@*12345abcdefghi", "Ashish@1234"));
	}

	// At least 18 alphanumeric characters Positive, char count=18
	@Test
	void test2() {
		assertEquals(true, testObj.changePassword("Ashish@*12345abcdefghi", "xZ@6sMIu&rbgGiRdFl"));
	}

	// At least 18 alphanumeric characters Negative, char count>18
	@Test
	void test3() {
		assertEquals(true, testObj.changePassword("Ashish@*12345abcdefghi", "iC#2EjUtgy$5bjW!zy0G"));
	}

	// No Special Character in new Password
	@Test
	void test4() {
		assertEquals(false, testObj.changePassword("Ashish@*12345abcdefghi", "Ashishdf12345abcdefghi"));
	}

	// Single Special Character in new Password
	@Test
	void test5() {
		assertEquals(true, testObj.changePassword("Ashish@*12345abcdefghi", "Ashishdf@12345abcdefghi"));
	}

	// Multiple Special Character in new Password
	@Test
	void test6() {
		assertEquals(true, testObj.changePassword("Ashish@*12345abcdefghi", "Ashishdf@#$12345abcdefghi"));
	}

	// Password without capital Letter
	@Test
	void test7() {
		assertEquals(false, testObj.changePassword("Ashish@*12345abcdefghi", "shishdf@#$12345abcdefghi"));
	}

	// Password without numeric value
	@Test
	void test8() {
		assertEquals(false, testObj.changePassword("Ashish@*12345abcdefghi", "shishdf@#$hyuabcdefghi"));
	}

	// Password without lower case letter
	@Test
	void test9() {
		assertEquals(false, testObj.changePassword("Ashish@*12345abcdefghi", "ABCDEFGHILJ123456@#$ASHY"));
	}

	// No duplicate repeat characters more than 4
	@Test
	void test10() {
		assertEquals(false, testObj.changePassword("Ashish@*12345abcdefghi", "ssssssf@#$hyuabcdefghi"));
	}

	// No More than 4 Special charcters
	@Test
	void test11() {
		assertEquals(false, testObj.changePassword("Ashish@*12345abcdefghi", "Ashishdf@#$##12345abcdefghi"));
	}
	// 50 % of password should not be a number
	@Test
	void test12() {
		assertEquals(false, testObj.changePassword("Ashish@*12345abcdefghi", "Ashishdf@12345678993234"));
	}
	// new password should not be similar to old password >80%
		@Test
		void test13() {
			assertEquals(false, testObj.changePassword("Ashish@*12345abcdefghi", "Ashish@*12345abcdefghius"));
		}
}
