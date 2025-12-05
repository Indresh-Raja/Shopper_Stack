package com.shopperstack.constants.endpoints;

public interface IEndpoints {
	public String Register="/shoppers";
	public String ReadShopper="/shoppers/{shopperId}";
	public String Products="/products";
	public String WishList="/shoppers/{shopperId}/wishlist";
	public String DeleteWishlist="/shoppers/{shopperId}/wishlist/{productId}";
	public String AddIntoCart="/shoppers/{shopperId}/carts";
}
