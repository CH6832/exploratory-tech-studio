#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""parser.py

A script to parse sentences using a context-free grammar and identify noun
phrase chunks.
"""


import nltk
import sys

nltk.download('punkt')

TERMINALS = """
Adj -> "country" | "dreadful" | "enigmatical" | "little" | "moist" | "red"
Adv -> "down" | "here" | "never"
Conj -> "and" | "until"
Det -> "a" | "an" | "his" | "my" | "the"
N -> "armchair" | "companion" | "day" | "door" | "hand" | "he" | "himself"
N -> "holmes" | "home" | "i" | "mess" | "paint" | "palm" | "pipe" | "she"
N -> "smile" | "thursday" | "walk" | "we" | "word"
P -> "at" | "before" | "in" | "of" | "on" | "to"
V -> "arrived" | "came" | "chuckled" | "had" | "lit" | "said" | "sat"
V -> "smiled" | "tell" | "were"
"""

NONTERMINALS = """
S -> N V
"""

# define the context-free grammar (CFG)
grammar = nltk.CFG.fromstring(NONTERMINALS + TERMINALS)
# create a parser based on the CFG
parser = nltk.ChartParser(grammar)

def main():
    # if filename specified, read sentence from file
    if len(sys.argv) == 2:
        with open(sys.argv[1]) as f:
            s = f.read()

    # otherwise, get sentence as input
    else:
        s = input("Sentence: ")

    # convert input into list of words
    s = preprocess(s)

    # attempt to parse sentence
    try:
        trees = list(parser.parse(s))
    except ValueError as e:
        print(e)
        return
    if not trees:
        print("Could not parse sentence.")
        return

    # print each tree with noun phrase chunks
    for tree in trees:
        tree.pretty_print()

        print("Noun Phrase Chunks")
        for np in np_chunk(tree):
            print(" ".join(np.flatten()))

def preprocess(sentence):
    """
    Convert `sentence` to a list of its words.
    Pre-process sentence by converting all characters to lowercase
    and removing any word that does not contain at least one alphabetic
    character.
    """
    tokenized = nltk.tokenize.word_tokenize(sentence)
    return [x.lower() for x in tokenized if x.isalpha()]

def np_chunk(tree):
    """
    Return a list of all noun phrase chunks in the sentence tree.
    A noun phrase chunk is defined as any subtree of the sentence
    whose label is "NP" that does not itself contain any other
    noun phrases as subtrees.
    """
    all_noun_pharse_chunks = []
    # subtrees() provides a list of all subtrees
    # contained in a tree 
    # https://ling-blogs.bu.edu/lx390f16/trees/
    for sbtr in tree.subtrees():
        # if label of the subtree i s "NP"
        if sbtr.label() == "NP":
            # add the subtreee with label "NP" to 
            # list with all noun phrases
            all_noun_pharse_chunks.append(sbtr)

    return all_noun_pharse_chunks

if __name__ == "__main__":
    main()
