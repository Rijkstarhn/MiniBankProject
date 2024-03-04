#!/bin/bash

# Define the Maven and Jib command to execute
MAVEN_COMMAND="mvn compile jib:build"

# Set parent directory to current working directory
PARENT_DIR="."

# List of folders to exclude
EXCLUDE_FOLDERS=("docker-compose")

# Iterate over each subfolder
for SUB_DIR in "$PARENT_DIR"/*; do
    if [ -d "$SUB_DIR" ]; then  # Check if it's a directory
        # Extract folder name
        FOLDER_NAME=$(basename "$SUB_DIR")

        # Check if the folder is NOT in the exclude list
        if [[ ! " ${EXCLUDE_FOLDERS[*]} " =~ " $FOLDER_NAME " ]]; then
            echo "Entering directory: $SUB_DIR"
            (cd "$SUB_DIR" && $MAVEN_COMMAND)
        else
            echo "Skipping directory: $SUB_DIR"
        fi
    fi
done
