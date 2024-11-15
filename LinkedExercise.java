//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: LinkedExercise class that contains methods for the linked list of Exercise class
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

public class LinkedExercise extends Object {

  private Exercise exercise;
  private LinkedExercise next;

  /**
   * Constructs a new LinkedExercise node with the given exercise data and next node.
   *
   * @param data the exercise to store in this node
   * @param next the next node in this list, which may be null
   */
  public LinkedExercise(Exercise data, LinkedExercise next) {
    this.exercise = data;
    this.next = next;
  }

  /**
   * Constructs a new LinkedExercise node with the given exercise data and no following node.
   *
   * @param data the exercise to store in this node
   */
  public LinkedExercise(Exercise data) {
    this.exercise = data;
  }

  /**
   * Gets the next linked node in the list.
   *
   * @return the reference to the node which follows this one in the list, may be null
   */
  public LinkedExercise getNext() {
    return this.next;
  }

  /**
   * Changes the node which follows this one to be the provided value.
   *
   * @param node the reference to set as the next node in the list, may be null
   */
  public void setNext(LinkedExercise node) {
    this.next = node;
  }

  /**
   * Gets the exercise stored in this linked node.
   *
   * @return the Exercise stored in this linked node
   */
  public Exercise getExercise() {
    return exercise;
  }

  /**
   * Returns a String representation of this linked exercise. The String will be in the format:
   * "exercise.toString() + " -> " if next field is NOT null, or "exercise.toString() + " -> END" if
   * the next field is null.
   *
   * @return a String representation of this linked exercise object
   * @see Exercise#toString()
   */
  public String toString() {
    if (this.next == null) {
      return exercise.toString() + " -> END";//at the end of a linked list
    }
    return exercise.toString() + " -> ";//points to next Workout
  }
}
