# Product BackLog

## 📋 機能一覧

### 1. 商品DRAFT登録機能
商品を下書き状態で登録できる（初期ステータスは必ずDRAFT）

**実装クラス**: `ProductCreateCommand`

**Input Parameters**:
- `sessionUserId` (String): セッションユーザーID
- `productName` (String): 商品名
- `price` (int): 価格

---

### 2. 商品公開機能
下書き状態の商品を公開する

**実装クラス**: `ProductPublishCommand`

**Input Parameters**:
- `productId` (String): 商品ID

---

### 3. 商品一覧取得機能
登録されている商品の一覧を取得する

**実装クラス**: `FindAllProductQuery`

**Input Parameters**: なし

---

### 4. 商品購入機能
公開されている商品を購入する

**実装クラス**: `OrderCommand`

**Input Parameters**:
- `userId` (String): ユーザーID
- `productId` (String): 商品ID

---

### 5. 残高チャージ機能
ユーザーの残高に金額をチャージする

**実装クラス**: `MoneyChargeCommand`

**Input Parameters**:
- `userId` (String): ユーザーID
- `amount` (int): チャージ金額

---

### 6. 残高不足バリデーション
残高が不足している場合は購入できないようにする

**実装クラス**: `OrderCommand` にバリデーションを追加

**ビジネスルール**:
- ユーザーの残高 < 商品価格 の場合、購入不可

---

### 7. 非公開商品バリデーション
非公開（DRAFT）の商品は購入できないようにする

**実装クラス**: `OrderCommand` にバリデーションを追加

**ビジネスルール**:
- 商品ステータスがDRAFTの場合、購入不可

---

### 8. VIPユーザーアップグレード機能
累計購入金額が1万円以上のユーザーをVIPにアップグレードできる

**実装クラス**: `UpgradeToVipUserCommand`

**Input Parameters**:
- `userId` (String): ユーザーID

**ビジネスルール**:
- 累計購入金額 >= 10,000円の場合のみアップグレード可能