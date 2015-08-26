#!/usr/bin/env bash

#PROJECT_PATH="base_project"
CURRENT_PATH=$(pwd)

echo $CURRENT_PATH

if [ -z "${CI_PULL_REQUEST}" ]; then
    exit 0
fi

#cd $PROJECT_PATH

./gradlew check -PdisablePreDex

#cd $CURRENT_PATH

echo "checkstyle"
cat app/build/reports/checkstyle/checkstyle.xml \
    | checkstyle_filter-git diff origin/master \
    | saddler report --require saddler/reporter/github --reporter Saddler::Reporter::Github::PullRequestReviewComment

echo "findbugs"
cat app/build/reports/findbugs/findbugs.xml \
    | findbugs_2_checkstyle convert \
    | checkstyle_filter-git diff origin/master \
    | saddler report --require saddler/reporter/github --reporter Saddler::Reporter::Github::PullRequestReviewComment

#echo "android lint"
#cat app/build/outputs/lint-results.xml \
#    | android_lint_translate_checkstyle_format translate \
#    | checkstyle_filter-git diff origin/master \
#    | saddler report --require saddler/reporter/github --reporter Saddler::Reporter::Github::PullRequestReviewComment
