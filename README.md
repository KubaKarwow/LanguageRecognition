# LanguageRecognition

Single-Layer Neural Network for Natural Language Classification
Overview

This project implements a single-layer neural network (perceptron) to classify texts in various languages. The perceptron uses the character frequency distribution of texts to distinguish between languages. This project is implemented from scratch in Java without using any machine learning libraries.
Directory Structure

Data Directory
        Create a directory for training data.
        Inside this directory, create subdirectories named after different languages (e.g., czech, slovak, etc.).
        Each subdirectory should contain at least 10 training texts in the respective language, with a minimum length of 2 paragraphs.
Test Data Directory
        Create a similar structure for test data, with at least 10 test texts for each language.

Data Preparation

Download training and test texts from sources such as Wikipedia.
Ensure texts are in plain text format (.txt), with only Latin alphabet characters.
Prepare a 26-element vector representing the proportion of each letter (a to z) in the text.  

  
Program Description

The single-layer neural network consists of K perceptrons, where K is the number of languages. Each perceptron is trained to recognize its respective language. Training continues until all training texts are correctly classified.
Training and Testing

Training: The network is trained using the prepared training texts. Each perceptron is adjusted to recognize the character frequency distribution of its language.

Testing: The network is tested on the test texts to evaluate classification accuracy.

User Interface: A text window allows users to input or paste any text, and the network classifies the language.

