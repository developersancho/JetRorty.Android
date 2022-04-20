package com.developersancho.testutils

import java.util.concurrent.TimeUnit

/**
 * Each test Robot in our application (UI test Robot or Unit test Robot) should extend BaseRobot
 */
open class BaseRobot {
    /**
     * Use the method to setup stuff in your Robot class before each test run
     */
    open fun setup() {
        //no base implementation
    }

    open fun tearsDown() {
        //no base implementation
    }
}

/**
 * Use the method to setup GIVEN step for any test (Unit and Instrumentation)
 * @param block - the block of code which needs to be executed in the GIVEN step
 * @return - robot instance of the test robot, it allows us to call robot methods directly
 */
fun <T : BaseRobot> TestRun<T>.GIVEN(
    block: T.() -> Unit
): T {
    //you can print something here
    return robot.apply(block)
}

/**
 * Use the method to setup WHEN step for any test (Unit and Instrumentation)
 * @param block - the block of code which needs to be executed in the WHEN step
 * @return - robot instance of the unit test, it allows us to call robot methods directly
 */
fun <T : BaseRobot> TestRun<T>.WHEN(
    block: T.() -> Unit
): T {
    //you can print something here too
    return robot.apply(block)
}

/**
 * Use the method to setup AND step for any test (Unit and Instrumentation)
 * @param block - the block of code which needs to be executed in the AND step
 * @return - robot instance of the unit test, it allows us to call robot methods directly
 */
fun <T : BaseRobot> TestRun<T>.AND(
    block: T.() -> Unit
): T {
    //you can print something here too
    return robot.apply(block)
}

/**
 * Use the method to setup THEN step for any test (Unit and Instrumentation)
 * @param block - the block of code which needs to be executed in the THEN step
 * @return - robot instance of the unit test, it allows us to call robot methods directly
 */
fun <T : BaseRobot> TestRun<T>.THEN(
    block: T.() -> Unit
): T {
    //you can print something here too
    return robot.apply(block)
}

/**
 * Simple data class which represents the test it self. We can add as much properties we need
 */
data class TestRun<T : BaseRobot>(
    val robot: T,
    val isUnitTest: Boolean
)

/**
 * Use the method to run single unit test
 * @param robot - Set your test robot (should extend BaseRobot)
 * @param block - the block of code which needs to be executed in our test. Usually we need to place inside all steps for the test
 * @return - TestRun data class instance, which includes the test name and the robot instance
 */
fun <T : BaseRobot> RUN_UNIT_TEST(
    robot: T,
    block: TestRun<T>.() -> Unit
): TestRun<T> {
    val startTime = System.nanoTime()

    println("*** UNIT TEST start ***")

    val testRun = TestRun(robot, true)
    block(testRun)

    val difference = System.nanoTime() - startTime

    println("*** time -> ${TimeUnit.NANOSECONDS.toMillis(difference)} ms ***")
    println("-----------------------------------------------------------------------------------------------")

    return testRun
}

/**
 * Use the method to run single UI test
 * @param robot - Set your test robot (should extend BaseRobot)
 * @param block - the block of code which needs to be executed in our test. Usually we need to place inside all steps for the test
 * @return - TestRun data class instance, which includes the test name and the robot instance
 */
fun <T : BaseRobot> RUN_UI_TEST(
    robot: T,
    block: TestRun<T>.() -> Unit
): TestRun<T> {
    val startTime = System.nanoTime()

    println("*** UI TEST start ***")

    val testRun = TestRun(robot, false)
    block(testRun)

    val difference = System.nanoTime() - startTime

    println("*** time -> ${TimeUnit.NANOSECONDS.toMillis(difference)} ms ***")
    println("-----------------------------------------------------------------------------------------------")

    return testRun
}