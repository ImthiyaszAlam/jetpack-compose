package com.imthiyas.usermanagementapp.repository

import com.imthiyas.usermanagementapp.model.ApiProduct
import com.imthiyas.usermanagementapp.model.Product
import com.imthiyas.usermanagementapp.network.RetrofitClient
import kotlinx.coroutines.delay

class ProductRepository {

    private val api = RetrofitClient.api

  suspend  fun getProducts(  limit: Int,
                             skip: Int): List<Product> {
      return api.getProducts(limit,skip).products.map { apiProduct ->

          Product(
              id = apiProduct.id,
              name = apiProduct.title,
              price = "₹${apiProduct.price}",
              isFavourite = false
          )
      }
    }


    suspend fun getProductDetails(id: Int): Product {

        val product = api.getProductDetails(id)

        return Product(
            id = product.id,
            name = product.title,
            price = "₹${product.price}",
            isFavourite = false
        )
    }


}
