package machines

import regex._
import dfa._
import scala.language.implicitConversions
// import scala.language.postPostfixOps


// TODO: Add your code below
given Conversion[Char, RegularLanguage] = Character(_)

given Conversion[String, RegularLanguage] = 
    _.foldRight[RegularLanguage](Epsilon)(Concat(_, _))

// helper to implicit conversion
def extractChars(l: RegularLanguage): Set[Char] = l match
    case Empty | Epsilon => Set()
    case Character(c) => Set(c)
    case Union(first, second) => 
        extractChars(first) union extractChars(second)
    case Concat(first, second) =>
        extractChars(first) union extractChars(second)
    case Star(pattern) => extractChars(pattern)
    

// given Conversion[RegularLanguage, DFA] = 
//     val chars = extractChars(_)
//     // _.toDFA(using chars)
//     regexToDFA(_, chars)

// union
extension (l: RegularLanguage)
    def ||(other: RegularLanguage): RegularLanguage = Union(l, other)
    def ~(other: RegularLanguage): RegularLanguage = Concat(l, other)
    def <*> = Star(l)
    def <+> = Concat(l, Star(l))
    def apply(n: Int) = 
        var result = l
        for i <- 1 to n-1 do result = Concat(l, result)
        result
    
    // contextual
    def toDFA(using s: Set[Char]): DFA = regexToDFA(l, s)
