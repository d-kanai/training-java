
# 🛒 ProductBacklog

![image](https://github.com/user-attachments/assets/a93ea144-4f83-4e36-9f87-a92b59ca7480)

## 📊 テーブル定義

### 🛍️ Product (state)
- `id` - 商品ID
- `name` - 商品名  
- `price` - 価格

### 📋 Order (state)
- `id` - 注文ID
- `productId` - 商品ID

### 💰 MoneyFlow (event)
- `id` - 取引ID
- `value` - 金額

---

## 🏗️ アーキテクチャ全体ルール

> **🎯 設計思想**: DDD、Clean Architectureのレイヤと依存関係のルールを持つ構造

### 📁 ディレクトリ構成
**Feature-based構成** (Screaming Architecture、Modular Monolith方式)

```
features/
└── module/
    ├── 🎨 presentation/     # HTTP requestからのinput
    ├── ⚙️ application/      # ユーザーアクションのワークフロー  
    ├── 🧠 domain/          # ロジック・ルールを集約
    └── 🗄️ infra/           # 永続化層
```

#### 🎨 Presentation層
- **役割**: HTTP requestからのinput
- **クラス例**: Input parameter DTO

#### ⚙️ Application層  
- **役割**: ユーザーアクションのワークフロー
- **Query Operation**: infraからデータを集め返す
- **Command Operation**: domain層を実行し、モデルをinfra層に永続化する
- **クラス例**: Usecase

#### 🧠 Domain層
- **役割**: ロジック・ルールはここに集める。モデルのライフサイクルが表現される
- **クラス例**: Domain Model, Value Object, Model Factory

#### 🗄️ Infra層
- **役割**: モデルを入力して永続層に保存する。現状はRDB
- **クラス例**: Repository

---

## 📝 代表的なクラスごとのルール

### 🔧 Usecase Class

- ✅ **1 class 1 public** - `run`メソッドのみにすること
- 🏷️ **命名規則** - UsecaseClass名がアクション内容がわかる命名にすること

```java
// ❌ Bad
ProductUpdateUsecase

// ✅ Good  
ProductPublishUsecase
```

### 🧩 Domain Model Class

- 🔒 **カプセル化** - fieldは必ずprivateにし、update・状態遷移はmodelのメソッドを通じて行うこと
- 🗣️ **ユーザー言語** - 状態遷移メソッドはユーザの言葉で命名すること

```java
// ❌ Bad
setStatus("publish")
update("publish") 

// ✅ Good
publish()
```

- 🏗️ **コンストラクタ** - 大切に扱う
  - ❌ **禁止**: all args constructor（状態遷移ルールが守られない）
  - ✅ **推奨**: static factory method or Model Factory Class などを利用すること

---

## 🧪 Spec・テストルール

### 📋 テストの種類

#### 🔍 TestA: No MockでのUsecaseテスト
**対象**: 全てのクラス、DBを接続してテストを行う

##### Query Operation
- **Given** 🗄️: RDBにデータをDataBuilder Classを利用して登録
- **When** ⚡: usecase execute  
- **Then** ✅: assert usecase return

##### Command Operation
- **Given** 🗄️: 必要であればquery operation同様事前データを登録
- **When** ⚡: usecase execute
- **Then** ✅: command結果通りの変化がDBに起きているかassert

#### 🎯 TestB: Domain層の網羅テスト
- **対象**: if、forなどをパターン網羅するようにテストをする

#### 🎬 TestC: 複数Usecaseを連続して呼び出すシナリオテスト
- **対象**: エンドツーエンドのワークフロー

---

## 📏 全てのTestで共通のルール

### ✍️ テスト名
- 🇯🇵 **日本語** でケースごとにテストの意図がわかるように表現をすること

### 📝 コメント
- **フェーズコメント** を必ずつけること:
  ```java
  //given
  //when  
  //then
  ```

### 🗂️ 整理テクニック
- `@Nested`、`@ParameterizedTest`など、整理テクニックを利用すること

---

## 🚀 開発環境

- **言語**: ☕ Java
- **ビルドツール**: 🔨 Maven
- **データベース**: 🗃️ SQLite
- **テストフレームワーク**: 🧪 JUnit 5
