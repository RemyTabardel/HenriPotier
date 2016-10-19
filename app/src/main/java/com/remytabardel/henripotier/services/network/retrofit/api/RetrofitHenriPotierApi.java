package com.remytabardel.henripotier.services.network.retrofit.api;

import com.remytabardel.henripotier.models.CartItem;
import com.remytabardel.henripotier.services.network.HenriPotierApi;
import com.remytabardel.henripotier.services.network.json.BookJson;
import com.remytabardel.henripotier.services.network.json.CommercialOffersJson;
import com.remytabardel.henripotier.services.network.retrofit.calls.RetrofitHenriPotierCalls;

import java.util.List;

import okhttp3.OkHttpClient;

/**
 * @author Remy Tabardel
 *         Retrofit implementation of HenriPotierApi
 */

public class RetrofitHenriPotierApi extends AbstractRetrofitApi<RetrofitHenriPotierCalls> implements HenriPotierApi {
    public RetrofitHenriPotierApi(String baseUrl, OkHttpClient okHttpClient) {
        super(baseUrl, okHttpClient, RetrofitHenriPotierCalls.class);
    }

    /**
     * @return list of books or null if not working
     */
    @Override public List<BookJson> getBooks() {
        return execute(getCalls().getBooks());
    }

    @Override public CommercialOffersJson getCommercialOffers(List<CartItem> items) {
        String listIsbn = items.get(0).getIsbn();

        for (int i = 1; i < items.size(); i++) {
            listIsbn += HenriPotierApi.ISBN_SEPARATOR + items.get(i).getIsbn();
        }

        return execute(getCalls().getCommercialOffers(listIsbn));
    }
}
