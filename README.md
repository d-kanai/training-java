# ProductBackLog



# table定義
- Product (state)
    - id
    - name
    - price
- Order (state)
    - id
    - productId
- MoneyFlow (event)
    - id
    - value

# アーキテクチャ全体ルール
- DDD, Clean Architectureのようなレイヤと依存関係を持つ構造
- feature baseのディレクトリ構成 (like screaming architecture, modular monolith)
- ディレクトリルール
    - features
        - module
            - presentation: http requestからのinput
                - class例：input parameter DTO
            - application: ユーザクションのワークフロー
                - query operation: infraからデータを集め返す
                - command operation: domain層を実行し、モデルをinfra層に永続化する 
                - class例：Usecase
            - domain: ロジック・ルールはここに集める。モデルのライフサイクルが表現される
                - class例：Domain Model, Value Object, Model Factory
            - infra: モデルを入力して永続層に保存する。現状はRDB
                - class例：Repository

                
# 代表的なクラスごとのルール
- Usecase Class
  - 1 class 1 public。runメソッドのみにすること
  - UsecaseClass名がアクション内容がわかる命名にすること
      - 例： bad: ProductUpdateUsecase => good: ProductPublishUsecase
- Domain Model Class
  - fieldは必ずprivateにし、update, 状態遷移はmodelのメソッドを通じて行うこと (カプセル化)
  - 状態遷移メソッドはユーザの言葉で命名すること
      - 例：bad: setStatus("publish"), update("publish") => good: publish()
  - コンストラクタを大切に扱う
      - all args constructorは禁止 (状態遷移ルールが守られない)
      - static factory method or Model Factory Classなどを利用すること
      

# Spec, Testルール
- Testの種類
    - TestA: No MockでのUsecaseに対するtest (全てのクラス, DBを接続してテストを行う)
        - query operation
            - given: RDBにデータをDataBuilder Classを利用して登録
            - when: usecase execute
            - then: assert usecase return
        - command operation
            - given: 必要であればquery operation同様事前データを登録
            - when: usecase execute
            - then: command結果通りの変化がDBに起きているかassert
    - TestB: domain modelに対する網羅test
        - if, forなどをパターン網羅するようにテストをする
    - TestC: 複数Usecaseを連続して呼び出すシナリオテスト
        - xxx
- 全てのTestで共通のルール
    - test名は日本語でケースごとにテストの意図がわかるように表現をすること
    - //given, //when, //then のフェーズコメントをつけること
    - @Nested, @ParameterizedTestなど、整理テクニックを利用すること