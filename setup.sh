#!/bin/bash

# Setup script for developers with access to private repository
# with assets

for remote in $( git remote ); do
    git remote remove $remote
done

git remote add origin https://github.com/FantAsylum/RGBot
git remote add private https://bitbucket.org/fantasylum/rgbot.git

git checkout -B assets

git pull private assets
git checkout master
git push -u origin master
