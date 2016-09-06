package com.wonders.xlab.electoniccarddemo;

import com.wonders.xlab.cardbag.data.entity.CardSearchEntity;
import com.wonders.xlab.cardbag.ui.cardsearch.CardSearchContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hua on 16/9/1.
 */

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
