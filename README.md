﻿# StreammingApp

 ● Steps to Run the Code

Install Java Development Kit (JDK) if not already installed.

Save the Java code in a file named StreamRecommendation.java.

Open terminal/command prompt and navigate to the file's directory.

Compile the code using:
javac StreamRecommendation.java

Run the compiled code using:
java StreamRecommendation

● Algorithm Explanation

Input: A list of live stream objects with stream ID, viewer count, and section name.

Goal: For each section, recommend top 3 streams with the highest viewers ensuring that no stream is repeated across sections.

Approach:

Group streams into sections and sort them by viewers in descending order using a max-heap (priority queue).

Use a set to keep track of used stream IDs to avoid duplication across sections.

Iterate through each section and pick streams until each section has 3 unique streams or the pool is exhausted.

Loop until no more updates can be made.

● Input JSON (input.json)

[
  {"id": 1, "viewers": 500, "section": "Gaming"},
  {"id": 2, "viewers": 600, "section": "Gaming"},
  {"id": 3, "viewers": 700, "section": "Gaming"},
  {"id": 4, "viewers": 400, "section": "Gaming"},
  {"id": 5, "viewers": 300, "section": "Music"},
  {"id": 6, "viewers": 800, "section": "Music"},
  {"id": 7, "viewers": 500, "section": "Music"},
  {"id": 8, "viewers": 200, "section": "Music"},
  {"id": 9, "viewers": 750, "section": "Tech"},
  {"id": 10, "viewers": 650, "section": "Tech"},
  {"id": 11, "viewers": 550, "section": "Tech"},
  {"id": 12, "viewers": 450, "section": "Tech"}
]

● Output JSON (output.json)

{
  "Gaming": [
    {"id": 3, "viewers": 700, "section": "Gaming"},
    {"id": 2, "viewers": 600, "section": "Gaming"},
    {"id": 1, "viewers": 500, "section": "Gaming"}
  ],
  "Music": [
    {"id": 6, "viewers": 800, "section": "Music"},
    {"id": 7, "viewers": 500, "section": "Music"},
    {"id": 5, "viewers": 300, "section": "Music"}
  ],
  "Tech": [
    {"id": 9, "viewers": 750, "section": "Tech"},
    {"id": 10, "viewers": 650, "section": "Tech"},
    {"id": 11, "viewers": 550, "section": "Tech"}
  ]
}

● Test Cases Used

Test Case 1 - Normal scenario with 3 sections and more than 3 streams each.

Test Case 2 - Edge case where some sections have fewer than 3 streams.

Test Case 3 - Scenario with duplicate stream IDs across sections.

Test Case 4 - Streams with equal viewers to test tie-breaking by order.

● Possible Failure Scenario and Solution

Scenario:
When total unique streams are fewer than required (i.e., not enough distinct streams to fill all sections with 3 recommendations).

Problem:
The current logic would leave some sections with fewer than 3 streams, potentially resulting in incomplete recommendations.

Solution for Better UX:

Allow fallback strategy: If after ensuring uniqueness, a section still has <3 streams, allow filling the remaining slots with already used streams but prioritize viewer count.

Or show a message: "Only X unique recommendations available for this section."


