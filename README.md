# Language Tutor

## Story

Your favorite cousin just started a language course in their high school and asked you to help with practicing. They are on their phone all the time, so you decide to write a Language Tutor app for them. You don't want to include an initial vocabulary database, just to make it easily extendable so that the students can add the new words and expressions they learn each week. The school has a wide international network, and you hope that you'll have a broader audience, so don't forget about proper localization!

## What are you going to learn?

- practice the usage of MVP Architecture
- practice the usage of `Room` persistence library
- use `Fragments`
- use `AsyncTask`
- utilize the localization feature in Android Studio

## Tasks


1. Create a menu screen

    - The app starts with a menu screen with buttons to navigate to each feature

2. Create a page where the user can add new words to the dictionary

    - The new word and its translation is saved to the dictionary database

3. Create a page where the user gets a random word from the dictionary and is prompted to enter the translation

    - The user has an option to choose the quantity of the questions at the beginning of the training from 3 given options (like 5, 10, 20)
    - The app tells the user if their translation is correct and navigates to the next question
    - The app tells the user if their translation is incorrect and shows the proper word, then waits for the user to navigate to the next question
    - A progress bar is displayed on the screen to show how many questions have been answered already and how many are left
    - The number of correct and incorrect guesses and the list of the missed words are saved into another table with the current date and time

4. Create a page displaying the results of past trainings

    - Display the date and the success ratio of the past trainings, ordered by date, latest first
    - As a reminder, display all the mistaken words with translations so they can memorize it

5. The app needs to be localized in multiple languages to reach a broader audience

    - The application has localization included, and shows all the labels in at least two languages (one of which is the device's language)

6. The app needs to have a custom icon and handle device rotation.

    - The app uses the MVP architecture pattern with `AsyncTasks` to implement the features mentioned above
    - The application has an icon other than the default
    - The application handles device rotation

7. [OPTIONAL] Add some extra logic that pops up problematic words more often

    - Incorrect answer for a question increases its probability of popping up in upcoming training sessions (up to a limit)
    - Correct answer for a question decreases its probability of popping up in upcoming training sessions(up to a limit)


## General requirements


None

## Starting repository

Follow [this link](https://journey.code.cool/v2/project/team/blueprint/language-tutor/java) to create your project repository.

## Background materials

- :exclamation: [Fragments](https://developer.android.com/guide/components/fragments)
- :exclamation: [Fragment tutorial](https://developer.android.com/training/basics/fragments/creating)
