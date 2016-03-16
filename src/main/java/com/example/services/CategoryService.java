package com.example.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.dao.CategoryDAO;
import com.example.models.Category;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryService {
	
	CategoryDAO categoryDAO = new CategoryDAO();
	
	@GET
	@Path("/getCategory/{id}")
    public Category getCategoryById(@PathParam("id") String id) {
		
		Category category = categoryDAO.getCategoryById(Long.parseLong(id));
		
		return category;
		
		//URL = http://localhost:8080/services/product/001
		//return Response.status(200).entity("product" + category).build();
    }
	
	@GET
	@Path("/all")
	public List<Category> getAllCategories() {
		
		List<Category> categories = categoryDAO.getAllCategories();
		
		return categories;
		
		//URL = http://localhost:8080/services/product/all
		//return Response.status(200).entity("get all products is called" + categories).build();
    }
	
	@GET
	@Path("/saveCategory/{name}/{description}")
	public Response saveNewProduct(@PathParam("name") String name, @PathParam("description") String description) {
		
		Category category = new Category();
		category.setName(name);
		category.setDescription(description);
		
		
		//add code to persist new Location
		if (categoryDAO.saveCategory(category)) {
			return Response.status(200).entity("FAILED to insert new category").build();
		}
		
		return Response.status(200).entity("category created successfully").build();
    }

}
