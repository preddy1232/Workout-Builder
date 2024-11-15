//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: This is the tester class for Workout Builder
// Course: CS 300 Fall 2023
//
// Author: Prithvi Reddy
// Email: pdreddy@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:
// Partner Email:
// Partner Lecturer's Name:
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

public class WorkoutBuilderTester {

  /**
   * Checks the correctness of the WorkoutBuilder.clear() method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testClear() {
    Exercise.resetIDNumbers();
    WorkoutBuilder workoutBuilder = new WorkoutBuilder();

    // Add some exercises to the list for testing
    Exercise exercise1 = new Exercise(WorkoutType.WARMUP, "Stretch");
    Exercise exercise2 = new Exercise(WorkoutType.PRIMARY, "Push-ups");
    Exercise exercise3 = new Exercise(WorkoutType.COOLDOWN, "Cool down");

    workoutBuilder.add(exercise1);
    workoutBuilder.add(exercise2);
    workoutBuilder.add(exercise3);

    // Verify that the size and counts are as expected before clearing
    if (workoutBuilder.size() == 3 && workoutBuilder.getWarmupCount() == 1
        && workoutBuilder.getPrimaryCount() == 1 && workoutBuilder.getCooldownCount() == 1) {

      // Clear the list
      workoutBuilder.clear();

      // Verify that the size and counts are now 0 after clearing
      if (workoutBuilder.size() == 0 && workoutBuilder.getWarmupCount() == 0
          && workoutBuilder.getPrimaryCount() == 0 && workoutBuilder.getCooldownCount() == 0
              && workoutBuilder.isEmpty()) {

        return true; 
      }
    }

    return false; 
  }

  /**
   * Checks the correctness of the WorkoutBuilder.add() method for adding exercises to the list.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testAddExercises() {
    Exercise.resetIDNumbers();
    WorkoutBuilder workoutBuilder = new WorkoutBuilder();

    // Add exercises to the list
    Exercise exercise1 = new Exercise(WorkoutType.WARMUP, "Stretch");
    Exercise exercise2 = new Exercise(WorkoutType.PRIMARY, "Push-ups");
    Exercise exercise3 = new Exercise(WorkoutType.COOLDOWN, "Cool down");
    Exercise exercise4 = new Exercise(WorkoutType.WARMUP, "Sit-Ups");

    workoutBuilder.add(exercise1);
    workoutBuilder.add(exercise2);
    workoutBuilder.add(exercise3);
    workoutBuilder.add(exercise4);
    

    // Verify that the size and counts are as expected
    if (workoutBuilder.size() == 4 &&
        workoutBuilder.getWarmupCount() == 2 &&
        workoutBuilder.getPrimaryCount() == 1 &&
        workoutBuilder.getCooldownCount() == 1) {

        // Add more exercises
      Exercise exercise5 = new Exercise(WorkoutType.COOLDOWN, "Cool down");
      Exercise exercise6 = new Exercise(WorkoutType.PRIMARY, "Basketball");
      Exercise exercise7 = new Exercise(WorkoutType.PRIMARY, "Cardio");

      workoutBuilder.add(exercise5);
      workoutBuilder.add(exercise6);
      workoutBuilder.add(exercise7);

        // Verify that the size and counts are updated correctly
        if (workoutBuilder.size() == 7 &&
            workoutBuilder.getWarmupCount() == 2 &&
            workoutBuilder.getPrimaryCount() == 3 &&
            workoutBuilder.getCooldownCount() == 2 && workoutBuilder.get(workoutBuilder.size() - 1).equals(exercise5)
            && workoutBuilder.get(workoutBuilder.size() - 5).equals(exercise7) && workoutBuilder.get(workoutBuilder.size() - 4).equals(exercise6)) {

            return true; // Test passed
        }
    }

    return false; // Test failed
  }

  /**
   * Checks the correctness of the WorkoutBuilder.removeExercise() method for removing exercises from the list.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testRemoveExercises() {
    Exercise.resetIDNumbers();
    WorkoutBuilder workoutBuilder = new WorkoutBuilder();

    // Add exercises to the list
    Exercise exercise1 = new Exercise(WorkoutType.WARMUP, "Stretch");
    Exercise exercise2 = new Exercise(WorkoutType.PRIMARY, "Push-ups");
    Exercise exercise3 = new Exercise(WorkoutType.COOLDOWN, "Cool down");
    Exercise exercise4 = new Exercise(WorkoutType.WARMUP, "Sit-Ups");
    Exercise exercise5 = new Exercise(WorkoutType.COOLDOWN, "Cool down");
    Exercise exercise6 = new Exercise(WorkoutType.PRIMARY, "Basketball");

    workoutBuilder.add(exercise1);
    workoutBuilder.add(exercise2);
    workoutBuilder.add(exercise3);
    workoutBuilder.add(exercise4);
    workoutBuilder.add(exercise5);
    workoutBuilder.add(exercise6);

    // Verify that the size and counts are as expected
    if (workoutBuilder.size() == 6 && workoutBuilder.getWarmupCount() == 2
        && workoutBuilder.getPrimaryCount() == 2 && workoutBuilder.getCooldownCount() == 2) {

      // Remove exercises
      workoutBuilder.removeExercise(WorkoutType.PRIMARY);
      workoutBuilder.removeExercise(WorkoutType.WARMUP);
      workoutBuilder.removeExercise(WorkoutType.COOLDOWN);



      // Verify that the size and counts are updated correctly
      if (workoutBuilder.size() == 3 && workoutBuilder.getWarmupCount() == 1
          && workoutBuilder.getPrimaryCount() == 1 && workoutBuilder.getCooldownCount() == 1 && 
          workoutBuilder.get(workoutBuilder.size() - 1).equals(exercise3) && workoutBuilder.get(workoutBuilder.size() - 2).equals(exercise2)
          && workoutBuilder.get(workoutBuilder.size() - 3).equals(exercise1)) {
        return true; 
      }
    }

    return false; // Test failed
  }


  /**
   * Checks the correctness of the WorkoutBuilder.get() method for retrieving exercises from the list.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetExercises() {
    Exercise.resetIDNumbers();
    WorkoutBuilder workoutBuilder = new WorkoutBuilder();

    // Add exercises to the list
    Exercise exercise1 = new Exercise(WorkoutType.WARMUP, "Stretch");
    Exercise exercise2 = new Exercise(WorkoutType.PRIMARY, "Push-ups");
    Exercise exercise3 = new Exercise(WorkoutType.COOLDOWN, "Cool down");

    workoutBuilder.add(exercise1);
    workoutBuilder.add(exercise2);
    workoutBuilder.add(exercise3);

    // Verify that the size is as expected
    if (workoutBuilder.size() == 3) {

      Exercise retrievedExercise1 = workoutBuilder.get(0);
      Exercise retrievedExercise2 = workoutBuilder.get(1);
      Exercise retrievedExercise3 = workoutBuilder.get(2);

      // Verify that the retrieved exercises match the added ones
      if (retrievedExercise1.equals(exercise1) && retrievedExercise2.equals(exercise2)
          && retrievedExercise3.equals(exercise3)) {

        return true; 
      }
    }

    return false; // Test failed
  }

  // a test suite method to run all your test methods
  public static boolean runAllTests() {
    boolean clear = testClear(), add = testAddExercises(), remove = testRemoveExercises(),
        get = testGetExercises();

    System.out.println("test clear: " + (clear ? "pass" : "FAIL"));
    System.out.println("test add: " + (add ? "pass" : "FAIL"));
    System.out.println("test remove: " + (remove ? "pass" : "FAIL"));
    System.out.println("test get: " + (get ? "pass" : "FAIL"));

    // TODO: add calls to any other test methods you write

    return clear; // TODO: replace this with the correct value
  }

  public static void main(String[] args) {
    runAllTests();
    demo();
  }

  /**
   * Helper method to display the size and the count of different boxes stored in a list of boxes
   * 
   * @param list a reference to an InventoryList object
   * @throws NullPointerException if list is null
   */
  private static void displaySizeCounts(WorkoutBuilder list) {
    System.out.println(
        "  Size: " + list.size() + ", warmupCount: " + list.getWarmupCount() + ", primaryCount: "
            + list.getPrimaryCount() + ", cooldownCount: " + list.getCooldownCount());
  }

  /**
   * Demo method showing how to use the implemented classes in P07 Inventory Storage System
   * 
   * @param args input arguments
   */
  public static void demo() {
    Exercise.resetIDNumbers();
    // Create a new empty WorkoutBuilder object
    WorkoutBuilder list = new WorkoutBuilder();
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // Add a primary exercise to an empty list
    list.add(new Exercise(WorkoutType.PRIMARY, "5k run")); // adds PRIMARY: 5k run (1)
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.WARMUP, "stretch")); // adds WARMUP: stretch (2) at the head
                                                           // of the list
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.PRIMARY, "bench press")); // adds PRIMARY: bench press (3)
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.WARMUP, "upright row")); // adds WARMUP: upright row (4) at
                                                               // the head of the list
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.WARMUP, "db bench")); // adds WARMUP: db bench (5) at the head
                                                            // of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // Add more exercises to list and display its contents
    list.add(new Exercise(WorkoutType.COOLDOWN, "stretch")); // adds COOLDOWN: stretch (6) at the
                                                             // end of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.add(new Exercise(WorkoutType.COOLDOWN, "sit-ups")); // adds COOLDOWN: sit-ups (7) at the
                                                             // end of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: sit-ups (7) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.add(new Exercise(WorkoutType.PRIMARY, "deadlift")); // adds PRIMARY: deadlift (8)
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: stretch (6) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(WorkoutType.WARMUP); // removes WARMUP: db bench (5)
    System.out.println(list); // prints list's content
    list.removeExercise(3); // removes PRIMARY: bench press (3) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    try {
      list.removeExercise(25); // tries to remove box #25
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // remove all warm-ups
    while (list.getWarmupCount() != 0) {
      list.removeExercise(WorkoutType.WARMUP);
    }
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(1); // removes PRIMARY: 5k run (1) from the list -> empty list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.add(new Exercise(WorkoutType.COOLDOWN, "walk")); // adds COOLDOWN: walk (9) to the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(8); // removes PRIMARY: deadlift (8) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: walk (9) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.add(new Exercise(WorkoutType.WARMUP, "pull-up")); // adds WARMUP: pull-up (10) to the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(10); // removes WARMUP: pull-up (10) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
  }

}
