package com.example.quizapplication

object QuestionData {
    
    val softwareQuestions = ArrayList<Question>().apply {
        add(
            Question(
                "Software",
                "What is SDLC?",
                "A process of designing software",
                "A programming language",
                "A development tool",
                "Computer hardware",
                "A",
                "SDLC (Software Development Life Cycle) is a structured process for designing and developing software."
            )
        )
        add(
            Question(
                "Software",
                "Which phase comes first in SDLC?",
                "Planning",
                "Testing",
                "Implementation",
                "Design",
                "A",
                "Planning is the first phase where requirements and scope are defined."
            )
        )
        add(
            Question(
                "Software",
                "What does API stand for?",
                "Application Programming Interface",
                "Application Process Integration",
                "Automated Program Interaction",
                "Advanced Programming Interface",
                "A",
                "API stands for Application Programming Interface - a set of rules for software communication."
            )
        )
        add(
            Question(
                "Software",
                "What is version control used for?",
                "To track and manage code changes",
                "To write code faster",
                "To test software",
                "To deploy applications",
                "A",
                "Version control tracks code changes and manages collaboration among developers."
            )
        )
        add(
            Question(
                "Software",
                "What is a bug in software?",
                "An error or defect in code",
                "A type of malware",
                "A security feature",
                "A programming language",
                "A",
                "A bug is an error in code that causes unexpected behavior or crashes."
            )
        )
        add(
            Question(
                "Software",
                "What does OOP stand for?",
                "Object-Oriented Programming",
                "Open Online Platform",
                "Operating System Protocol",
                "Output Optimization Process",
                "A",
                "OOP is a programming paradigm based on objects and classes."
            )
        )
        add(
            Question(
                "Software",
                "What is refactoring?",
                "Improving code structure without changing functionality",
                "Writing new code",
                "Finding bugs",
                "Deploying software",
                "A",
                "Refactoring improves code quality and maintainability while keeping functionality the same."
            )
        )
        add(
            Question(
                "Software",
                "What is a framework in software development?",
                "A reusable set of tools and libraries",
                "A programming language",
                "A type of database",
                "A testing tool",
                "A",
                "A framework provides pre-built components for faster development."
            )
        )
        add(
            Question(
                "Software",
                "What is code review?",
                "Examination of code by other developers",
                "Testing software",
                "Compiling code",
                "Deploying to production",
                "A",
                "Code review ensures quality, catches bugs, and shares knowledge among team members."
            )
        )
        add(
            Question(
                "Software",
                "What is agile methodology?",
                "An iterative approach to software development",
                "A type of database",
                "A programming language",
                "A testing framework",
                "A",
                "Agile is an iterative development method emphasizing flexibility and customer collaboration."
            )
        )
    }

    val dataStructureQuestions = ArrayList<Question>().apply {
        add(
            Question(
                "DS",
                "What is an array?",
                "A collection of elements stored in contiguous memory",
                "A type of sorting algorithm",
                "A programming pattern",
                "A database structure",
                "A",
                "An array stores multiple elements of the same type in consecutive memory locations."
            )
        )
        add(
            Question(
                "DS",
                "What is a linked list?",
                "A linear data structure with nodes connected by pointers",
                "A type of array",
                "A sorting method",
                "A tree structure",
                "A",
                "A linked list uses nodes with pointers to link to the next element, allowing dynamic sizing."
            )
        )
        add(
            Question(
                "DS",
                "What is the time complexity of binary search?",
                "O(log n)",
                "O(n)",
                "O(n^2)",
                "O(1)",
                "A",
                "Binary search has logarithmic time complexity O(log n) on sorted data."
            )
        )
        add(
            Question(
                "DS",
                "What is a stack?",
                "A LIFO (Last In First Out) data structure",
                "A FIFO data structure",
                "A tree structure",
                "A sorting algorithm",
                "A",
                "A stack follows LIFO principle where the last added element is removed first."
            )
        )
        add(
            Question(
                "DS",
                "What is a queue?",
                "A FIFO (First In First Out) data structure",
                "A LIFO data structure",
                "A sorting method",
                "A tree structure",
                "A",
                "A queue follows FIFO principle where the first added element is removed first."
            )
        )
        add(
            Question(
                "DS",
                "What is a binary tree?",
                "A tree where each node has at most two children",
                "A tree with multiple children",
                "A linear data structure",
                "A sorting algorithm",
                "A",
                "A binary tree is a hierarchical structure where each node has up to two child nodes."
            )
        )
        add(
            Question(
                "DS",
                "What is the best case time complexity of quicksort?",
                "O(n log n)",
                "O(n^2)",
                "O(n)",
                "O(log n)",
                "A",
                "Quicksort's best case is O(n log n) when the pivot divides the array evenly."
            )
        )
        add(
            Question(
                "DS",
                "What is a hash table used for?",
                "Fast lookups using key-value pairs",
                "Sorting data",
                "Building trees",
                "Linear searching",
                "A",
                "Hash tables provide efficient O(1) average case lookups using hash functions."
            )
        )
        add(
            Question(
                "DS",
                "What is the difference between recursion and iteration?",
                "Recursion uses function calls; iteration uses loops",
                "Both are identical",
                "Iteration is not used in algorithms",
                "Recursion is always faster",
                "A",
                "Recursion calls functions repeatedly; iteration uses loops to repeat code."
            )
        )
        add(
            Question(
                "DS",
                "What is a graph?",
                "A non-linear data structure with nodes and edges",
                "A type of array",
                "A sorting method",
                "A linear structure",
                "A",
                "A graph consists of vertices (nodes) connected by edges, used for complex relationships."
            )
        )
    }
}
