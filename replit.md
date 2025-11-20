# Project Information

## Overview
JavaプロジェクトでJUnit 5を使用したテスト環境

## Test Execution Rules
- テスト実行時は高速に実行すること
- `restart_workflow`でテストを実行後、sleepやログ確認は不要
- ユーザーはプロジェクトビューでログを直接確認できる
- テスト結果の詳細が必要な場合のみログを確認する

## Development Environment
- Java 1.8
- Maven
- JUnit 5
- SQLite

## Workflow
- Run Tests: `mvn test`を実行してテストを実行
