//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: WorkoutBuilder class that implements ListAdt class and contains methods for getting and
//////////////// changing the linkedExercise workouts.
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

public class WorkoutBuilder extends Object implements ListADT<Exercise> {

  private LinkedExercise head;
  private LinkedExercise tail;
  private int size;
  private int warmupCount;
  private int primaryCount;
  private int cooldownCount;

  /**
   * Accesses the total number of elements in this WorkoutBuilder list.
   *
   * @return the size of this list
   */
  public int size() {
    return size;
  }

  /**
   * Accesses the number of warm-up exercises stored in this WorkoutBuilder list.
   *
   * @return the count of exercises with WorkoutType equal to WARMUP
   */
  public int getWarmupCount() {
    return warmupCount;
  }

  /**
   * Accesses the number of primary exercises stored in this WorkoutBuilder list.
   *
   * @return the count of exercises with WorkoutType equal to PRIMARY
   */
  public int getPrimaryCount() {
    return primaryCount;
  }

  /**
   * Accesses the number of cool-down exercises stored in this WorkoutBuilder list.
   *
   * @return the count of exercises with WorkoutType equal to COOLDOWN
   */
  public int getCooldownCount() {
    return cooldownCount;
  }

  /**
   * Checks whether this WorkoutBuilder list is empty.
   *
   * @return true if this list contains no elements and neither its head nor tail refer to
   *         LinkedExercise objects
   */
  public boolean isEmpty() {
    return size == 0 && head == null && tail == null;
  }

  /**
   * Removes all elements from this list. The list will be empty after this call returns.
   */
  public void clear() {
    head = null;
    tail = null;
    size = 0;
    warmupCount = 0;
    primaryCount = 0;
    cooldownCount = 0;
  }

  /**
   * Finds the index of a given exercise in this WorkoutBuilder list, if it is present.
   *
   * @param findObject the exercise to search for in this list
   * @return the index of this object in the list if it is present; -1 if it is not
   */
  public int indexOf(Exercise findObject) {
    LinkedExercise current = head;
    int index = 0;

    while (current != null) {
      if (current.getExercise().equals(findObject)) {
        return index;
      }

      current = current.getNext();
      index++;
    }

    return -1;
  }

  /**
   * Returns the exercise stored at the given index of this list without removing it.
   *
   * @param index position within this list
   * @return the exercise stored at the given index of this list
   * @throws IndexOutOfBoundsException if the index is not valid for the current size of this list
   */
  public Exercise get(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);//throws error
    }
    LinkedExercise current = head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }

    return current.getExercise();
  }

  /**
   * Adds the provided Exercise to the appropriate position in the list for its WorkoutType, and
   * increments the corresponding counter fields. WARMUP: adds to the FRONT (head) of the list
   * PRIMARY: adds after all warm-ups and before any cool-downs COOLDOWN: adds to the END (tail) of
   * the list
   *
   * @param newObject the exercise to add to the WorkoutBuilder list
   */
  public void add(Exercise newObject) {
    if (newObject == null) {
      throw new IllegalArgumentException("Cannot add null exercise to the list");
    }

    if (isEmpty()) {
      head = new LinkedExercise(newObject, null);
      tail = head;
      size++;
      updateCounters(newObject.getType(), 1);//called update Counters private helper method
    } 
    else {
      WorkoutType newObjectType = newObject.getType();

      if (newObjectType == WorkoutType.WARMUP) {
        LinkedExercise newHead = new LinkedExercise(newObject, head);
        head = newHead;
        size++;
        updateCounters(WorkoutType.WARMUP, 1);
      } 
      else if (newObjectType == WorkoutType.PRIMARY) {
        LinkedExercise current = head;

        // Found the position to insert the primary exercise
        while (current.getNext() != null
            && current.getNext().getExercise().getType() == WorkoutType.WARMUP) {
          current = current.getNext();
        }

        LinkedExercise newPrimaryNode = new LinkedExercise(newObject, current.getNext());
        current.setNext(newPrimaryNode);

        if (current == tail) {
          // Updated tail if the primary exercise is added at the end
          tail = newPrimaryNode;
        }

        size++;
        updateCounters(WorkoutType.PRIMARY, 1);
      } 
      else if (newObjectType == WorkoutType.COOLDOWN) {
        LinkedExercise newTail = new LinkedExercise(newObject, null);
        tail.setNext(newTail);
        tail = newTail;
        size++;
        updateCounters(WorkoutType.COOLDOWN, 1);
      } 
      else {
        throw new IllegalArgumentException("Unsupported WorkoutType: " + newObjectType);
      }
    }
  }
  
  /**
   * Updates the counters for the type of exercise when called
   * @param type of WorkOut
   * @param count of the Workout
   */
  private void updateCounters(WorkoutType type, int count) {
    // Update the corresponding counter fields based on the WorkoutType
    if (type == WorkoutType.WARMUP) {
      warmupCount += count;
    } else if (type == WorkoutType.PRIMARY) {
      primaryCount += count;
    } else if (type == WorkoutType.COOLDOWN) {
      cooldownCount += count;//updates count for each workout type
    }
  }

  /**
   * Removes an exercise of the provided type from the list, if one exists, and decrements the
   * corresponding counter fields. WARMUP: removes the FIRST (head) element from the list PRIMARY:
   * removes the FIRST exercise of type PRIMARY from the list COOLDOWN: removes the LAST (tail)
   * element from the list
   *
   * @param type the type of exercise to remove from the list
   * @return the exercise object that has been removed from the list
   * @throws NoSuchElementException if there are no exercises in the list with the given WorkoutType
   */
  public Exercise removeExercise(WorkoutType type) throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Cannot remove exercise from an empty list");
    }

    Exercise removedExercise = null;

    if (type == WorkoutType.WARMUP) {
      removedExercise = removeWarmup();
    } else if (type == WorkoutType.PRIMARY) {
      removedExercise = removePrimary();
    } else if (type == WorkoutType.COOLDOWN) {
      removedExercise = removeCooldown();
    } else {
      throw new IllegalArgumentException("Unsupported WorkoutType: " + type);
    }

    return removedExercise;
  }

  /**
   * Private helper method for remove Warmup
   * @return Exercise after it removes warmup
   * @throws NoSuchElementException
   */
  private Exercise removeWarmup() throws NoSuchElementException {
    if (head == null || head.getExercise().getType() != WorkoutType.WARMUP) {
      throw new NoSuchElementException("No warm-up exercises in the list");
    }

    Exercise removedExercise = head.getExercise();
    head = head.getNext();
    size--;//decrease size

    updateCounters(WorkoutType.WARMUP, -1);

    if (head == null) {
      // List is empty after removal
      tail = null;
    }

    return removedExercise;
  }

  /**
   * Private helper method for remove Primary workout
   * @return Exercise after removing primary workout
   * @throws NoSuchElementException
   */
  private Exercise removePrimary() throws NoSuchElementException {
    LinkedExercise current = head;
    LinkedExercise previous = null;

    while (current != null && current.getExercise().getType() != WorkoutType.PRIMARY) {
      previous = current;
      current = current.getNext();
    }

    if (current == null) {
      throw new NoSuchElementException("No primary exercises in the list");
    }

    Exercise removedExercise = current.getExercise();

    if (previous == null) {
      head = current.getNext();
    } else {
      previous.setNext(current.getNext());
    }

    if (current.getNext() == null) {
      tail = previous;
    }

    size--;

    updateCounters(WorkoutType.PRIMARY, -1);//call update counters to decrease primary workout by 1

    return removedExercise;
  }

  /**
   * Private helper method for remove Cooldown workout
   * @return Exercise after removing Cooldown workout
   * @throws NoSuchElementException
   */
  private Exercise removeCooldown() throws NoSuchElementException {
    if (tail == null || tail.getExercise().getType() != WorkoutType.COOLDOWN) {
      throw new NoSuchElementException("No cool-down exercises in the list");
    }

    Exercise removedExercise = tail.getExercise();

    if (head == tail) {
      head = null;
      tail = null;
    } else {
      LinkedExercise current = head;
      while (current.getNext() != tail) {
        current = current.getNext();
      }
      current.setNext(null);
      tail = current;
    }

    size--;//decrease size

    updateCounters(WorkoutType.COOLDOWN, -1);

    return removedExercise;
  }

  /**
   * Removes the exercise with the provided ID number from the list, if it is present, and adjusts
   * any corresponding counter fields as necessary. This method uses a linear search algorithm.
   *
   * @param exerciseID the unique identifier of the exercise to be removed
   * @return the exercise object that has been removed from the list
   * @throws NoSuchElementException if no exercises in the list match the provided exerciseID number
   */
  public Exercise removeExercise(int exerciseID) throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Cannot remove exercise from an empty list");
    }

    Exercise removedExercise = null;
    LinkedExercise current = head;
    LinkedExercise previous = null;

    while (current != null && current.getExercise().getExerciseID() != exerciseID) {
      previous = current;
      current = current.getNext();
    }

    if (current == null) {
      throw new NoSuchElementException("ERROR: " + exerciseID + " is not present in the list!");
    }

    removedExercise = current.getExercise();

    if (previous == null) {
      // Removing the first exercise in the list
      head = current.getNext();
    } else {
      previous.setNext(current.getNext());
    }

    if (current.getNext() == null) {
      // Removing the last exercise in the list
      tail = previous;
    }

    size--;

    WorkoutType removedType = removedExercise.getType();
    updateCounters(removedType, -1);

    return removedExercise;
  }

  /**
   * Returns a String representation of the contents of this list, as the concatenated String
   * representations of all LinkedExercise nodes in this list.
   *
   * @return a String representation of the content of this list. If this list is empty, an empty
   *         String ("") will be returned.
   */
  @Override
  public String toString() {
    String result = "";

    LinkedExercise current = head;
    while (current != null) {
      result += current.getExercise().toString();

      if (current.getNext() != null) {
        result += " -> ";
      } else
        result += " -> END";

      current = current.getNext();
    }

    return result;
  }

}
