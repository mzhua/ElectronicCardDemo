# 电子会员卡SDK使用文档

## 版本支持
minSdkVersion 15

---

##  接入方式

### Android Studio

* 在module的build.gradle中加入依赖

   `compile 'com.wonders.xlab.cardbag:card-bag:1.0.5'`
   
   如图
   
   ![添加依赖](art/添加依赖.png)

---

## 如何调用

* 打开电子会员卡

	```
	CBag.get().start();
	```
	
* 直接调用扫一扫

	```
	XQrScanner.getInstance().startForResult(this, 12);
	```

* 重写onActivityResult接收扫描条形码结果,返回结果为字符串（如果没有单独的调用扫描条形码模块的功能，则不需要添加）

	```
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12 && resultCode == RESULT_OK) {
            String result = data.getStringExtra(XQrScanner.EXTRA_RESULT_BAR_OR_CODE_STRING);
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }
	```



## 设置自定义属性

电子会员卡SDK可以自定义3个属性，主要是为了最大程度的配合APP的设计，具体可自定义属性如下

属性名 |资源类型| 解释 | 默认值 | 可用值
-------|------|-----|---
cbTopBarTitleColor|color|TopBar标题颜色（返回按钮和菜单的颜色也是随着标题颜色）|`@color/cbTextBlack `|颜色资源
cbTopBarBackground|color|TopBar背景颜色|`@android:color/white`|颜色资源
cbTopBarGravity|integer|TopBar标题的对其方式|`@integer/cbTopBarGravityLeft`|`@integer/cbTopBarGravityLeft`</br>`@integer/cbTopBarGravityCenter`


----

## 自定义搜索接口

电子会员卡中有一个搜索界面，可以根据输入的卡片名称进行搜索，如果需要提供搜索功能，则通过下面方法来进行（不实现下面功能也可以进行完整的流程，只是搜索界面只能添加空白的新卡片，无法选择预设的模板卡片）

1. 新建一个类，并且实现CardSearchContract.Model接口。在其中完成卡片的搜索功能，查询结束后，调用callback.onSuccess进行回调即可

	```
public class CardSearchModelImpl implements CardSearchContract.Model {
    /**
     * TODO
     * 此处仅为模拟数据,具体数据来源可以自行修改,数据请求完毕后调用 callback.onSuccess进行回调即可
     * @param cardName
     * @param callback
     */
    @Override
    public void searchByCardName(String cardName, Callback<List<CardSearchEntity.ResultsEntity>> callback) {
        List<CardSearchEntity.ResultsEntity> cards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CardSearchEntity.ResultsEntity entity = new CardSearchEntity.ResultsEntity();
            entity.setCard_img_url("http://img.taopic.com/uploads/allimg/121217/267860-12121H0414065.jpg");
            entity.setCard_name("name" + i);
            entity.setObjectId("" + i);
            cards.add(entity);
        }
        callback.onSuccess(cards);
    }

    @Override
    public void onDestroy() {

    }
}
```

2. 初始化CBag时设置搜索接口的实现

	```
	CBag.get()
                .setCardSearchModel(new CardSearchModelImpl())
                .start(this);
	```

---

## Proguard配置

```
-dontshrink
-keep class com.wonders.xlab.cardbag.** { *; }

-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }

-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

-dontwarn okio.**
-keep class okio.**{*;}

-keep class com.squareup.** {*;}
```