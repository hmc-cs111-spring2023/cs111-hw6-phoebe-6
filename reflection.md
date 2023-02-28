# Reflection on implementing regular expressions of a DSL

## Which operators were easiest to implement and why?

The binary operators for union and concat were easiest to implement. Since normal math operations (addition/subtraction) are binary operators in many programming languages, the concept is easier to grasp. These operators are also syntactic sugar operators, which means there are also less logic to implement.

## Which operators were most difficult to implement and why?

The repetition operator was the most difficult to implement. I was able to find an imperative way of doing the operator quickly (instantiating a var and updating it with a for loop), but I got confused when attempting to do this recursively. I conflated Concat as a trait rather than a constructor (leading me to attempt to write `l.Concat` at one point). It also took a bit of time to figure out where to put the recursive call.

## Comment on the design of this internal DSL

Write a few brief paragraphs that discuss:

- What works about this design? (For example, what things seem easy and
  natural to say, using the DSL?)
- What doesn't work about this design? (For example, what things seem
  cumbersome to say?)
- Think of a syntactic change that might make the language better. How would
  you implement it _or_ what features of Scala would prevent you from
  implementing it? (You don't have to write code for this part. You could say
  "I would use extension to..." or "Scala's rules for valid
  identifiers prevent...")

This design works really well for writing math grammars because the addition operators we added are wrappers around existing functionality to correspond to more math-like formulas. This makes it easy to create representation of things that contains lots of patterns. I can imagine this DSL being used for representing in knitting or embroidered patterns. Stitches can be represented with Characters and the repetition operator. The other operators can be used to show nesting or crossing of stitches. 

Implicit conversion between custom types are significantly more difficult than from a built-in type. Concatenation can also become confusing when many operators are used at once. The telephone example in Program is relatively easy to read because of the simple 3-3-4 structure of US numbers, but more complicated systems like library classifications may require even more syntatic sugar for ease of use.

One syntactic change that might make the language better is to add an extension for an operator that asks if a particular pattern exists with a question mark. Since regex is used for matching, it is likely that end-users will make many comparisons between strings and a regex expression. Another aspect may be creating precedence between operators with Scala's enforced precedence rules to make operations more in line with how we may consider them. For example, we may want to rename concatenation as a plus sign to prioritize union operations over concatenation.
