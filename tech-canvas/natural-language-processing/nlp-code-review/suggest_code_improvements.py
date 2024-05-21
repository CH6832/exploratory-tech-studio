import nltk

"""main.py

We define a function analyze_comment() that takes a comment as input.
We tokenize the comment using nltk.word_tokenize() and then perform part-of-speech tagging using nltk.pos_tag() to identify words and their respective parts of speech.
We analyze the tagged words to find specific patterns indicative of suggestions for code improvement. For example, if the comment contains adjectives like "efficient" or "optimized," it suggests optimizing the function for efficiency. Similarly, if the comment contains nouns like "performance" or "speed," it suggests optimizing for better performance.
Finally, we return a list of suggestions based on the analysis.
"""

def suggest_code_improvements(comment):
    # Tokenize the comment
    tokens = nltk.word_tokenize(comment)
    
    # Part-of-speech tagging
    tags = nltk.pos_tag(tokens)
    
    # Analyze tags for suggestions
    suggestions = []
    for word, tag in tags:
        if tag == 'JJ' and word.lower() in ['efficient', 'optimized']:
            suggestions.append("Consider optimizing this function for efficiency.")
        elif tag == 'NN' and word.lower() in ['performance', 'speed']:
            suggestions.append("This function could be optimized for better performance.")
    
    return suggestions

def analyze_file(filename):
    with open(filename, 'r') as file:
        code = file.read()
    
    # Tokenize code comments
    comments = []
    lines = code.split('\n')
    for line in lines:
        if '#' in line:
            comments.append(line.split('#', 1)[1].strip())
    
    # Analyze comments and print suggestions
    print("Code Review Suggestions:")
    for comment in comments:
        suggestions = suggest_code_improvements(comment)
        if suggestions:
            print(f"Comment: {comment}")
            for suggestion in suggestions:
                print(f"- {suggestion}")

def main():
    if len(sys.argv) != 2:
        print("Usage: python code_review.py <input_file>")
        sys.exit(1)
    
    filename = sys.argv[1]
    analyze_file(filename)

if __name__ == "__main__":
    main()
