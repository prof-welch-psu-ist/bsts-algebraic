## Some (recursive) methods for `BSTree`

Do all these methods recursively. They will each do some operation on the 
immutable `BSTree` (Binary Search Tree) type defined this week in class.

**Instructions:**

1. read the `exercises.md` in the root directory of this repo and implement each as a static method in the `Exercises` class.
2. you should be using JDK23 for these + IntelliJ 2024.X (more recent the better)
3. write one or two minimal `@Test`s for each of these 
methods in the green test dir to confirm your implementation is working 
4. do as many of these as you can -- the last one you'll need to use a higher-order 
`Function` 
   * [somewhat OK resource on higher order functions and predicates in java](https://softwarepatternslexicon.com/patterns-java/11/2/) 
   
### method: `sumAll`

Write a method, `sumAll` that takes a `BSTree` and produces the sum of all the nodes.

```java
BSTree tr = BSTree.empty()
            .insert(10)
            .insert(5)
            .insert(20)
            .insert(15);
int res = Exercises.sumAll(tr);
System.out.println(res); // sum of all nodes is 50
```
### method: `sumLeafs`

Now write a variant, `sumLeafs` that takes a `BSTree` and produces/returns the 
sum of only the leaf nodes in the bst. 

```java
// declaring and initializing a sample bst...
// will be using `var` for decls here on out
var tr = BSTree.empty() 
        .insert(10)
        .insert(5)
        .insert(20)
        .insert(15);
int res = Exercises.sumLeafs(tr);
System.out.println(res); // 20 (sum of leaf nodes 5 and 15)
```
### method: `maxDepth`

Write a method `maxDepth` that takes a `BSTree` and computes the depth (or height) of the tree, 
defined as the number of edges from the root node to the 
deepest leaf node.

```java
var tr = BSTree.empty()
        .insert(10)
        .insert(5)
        .insert(20)
        .insert(25);
int res = Exercises.depth(tr);
System.out.println(res); // 3
```

### method: `average`

Write a method `average` that takes a `BSTree` as a parameter and returns the 
average of all nodes contained in the tree.

```java
var tr = BSTree.empty()
        .insert(8)
        .insert(2);
int res = Exercises.average(tr);
System.out.println(res); // 5
```

### method: `toList`

Write a method `toList` that takes a `BSTree` and performs a traversal over it
and returns a `TreeSet<Integer>` of the elements in ascending order.

```java
var tr = BSTree.empty()
        .insert(10)
        .insert(5)
        .insert(20)
        .insert(15);
TreeSet<Integer> res = Exercises.toList(tr);
System.out.println(res); // [5, 10, 15, 20]
```

### method: `pathSum`

Write a method `pathSum` that takes a `BSTree` and returns true only if there 
exists a path from the root node to any leaf whose values on the path down sum 
to a given target value.

```java
var tr = BSTree.empty()
        .insert(10)
        .insert(5)
        .insert(15)
        .insert(1)
        .insert(7);
// any leaf nodes with a path down that sums to 22?
boolean res = Exercises.pathSum(tr, 22); // True (10 -> 5 -> 7)
System.out.println(res); // true
```

### method: `mirror`

Write a method `mirror` that returns a `BSTree` where the 
left and right children of each node are swapped, creating a 
mirror image of the original tree.

```java 
var tr = BSTree.empty()
        .insert(10)
        .insert(5)
        .insert(15);
BSTree mirrored = Exercises.mirror(tr);
// should print a mirrored preorder traversal
System.out.println(mirrored.preOrder()); 
```

### method: `allSatisfy`

Write a method `allSatisfy` that takes a `BSTree` and a `Function<Integer, Boolean> f`. 
> i.e.: the method takes a function `f : Integer -> Boolean`

The method should return true only if each node in the tree passed satisfies the 
provided function `f`.

```java 
var tr = BSTree.empty()
          .insert(10)
          .insert(5)
          .insert(20)
          .insert(15);

// example 1: check if all nodes are greater than 0
boolean allPositive = Exercises.allSatisfy(tr, (Integer x) -> x > 0);
System.out.println(allPositive); // true, since all nodes are positive

// example 2: check if all nodes are less than 10 
// (can actually omit the `Integer` on the param `x`)
boolean allLessThan10 = Exercises.allSatisfy(tr, x -> x < 10);
System.out.println(allLessThan10); // false, since 10, 20, and 15 are not less than 10

// example 3: check if all nodes are even
boolean allEven = Exercises.allSatisfy(tr, x -> x % 2 == 0);
System.out.println(allEven); // false, since 5 and 15 are odd

// example 4: check if all nodes are divisible by 5
boolean allDivisibleBy5 = Exercises.allSatisfy(tr, x -> x % 5 == 0);
System.out.println(allDivisibleBy5); // true, since all nodes are divisible by 5```
```
