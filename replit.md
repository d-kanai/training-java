# Project Information

## Overview
JavaプロジェクトでJUnit 5を使用したテスト環境

## Test Execution Rules
- テスト実行時は高速に実行すること
- `restart_workflow`でテストを実行後、sleepでの待機は不要
- テスト実行後は必ずログを確認して結果（成功/失敗、テスト数など）をユーザーに報告する
- ユーザーは詳細をプロジェクトビューで確認できる

## Development Environment
- Java 1.8
- Maven
- JUnit 5
- SQLite

## Workflow
- Run Tests: `mvn test`を実行してテストを実行
