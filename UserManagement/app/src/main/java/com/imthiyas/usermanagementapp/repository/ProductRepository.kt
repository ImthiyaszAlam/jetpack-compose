package com.imthiyas.usermanagementapp.repository

import com.imthiyas.usermanagementapp.model.ApiProduct
import com.imthiyas.usermanagementapp.model.Product
import com.imthiyas.usermanagementapp.network.RetrofitClient
import kotlinx.coroutines.delay

class ProductRepository {

    private val api = RetrofitClient.api

  suspend  fun getProducts(): List<Product> {
      return api.getProducts().products.map { apiProduct ->

          Product(
              id = apiProduct.id,
              name = apiProduct.title,
              price = "₹${apiProduct.price}",
              isFavourite = false
          )
      }
    }
}
