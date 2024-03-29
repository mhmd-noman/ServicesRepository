package product.management.services.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.beans.Product;
import common.exception.handling.BaseException;
import common.utilities.constants.CommonConstants;
import common.utilities.methods.Utils;
import database.manager.methods.AbstractCommonDbMethods;
import product.management.services.beans.ProductManagementRequest;
import product.management.services.utils.Constants;

public class ProductManagementServicesDaoImpl extends AbstractProductManagementServicesDao {
	private static final Logger logger = LoggerFactory.getLogger(ProductManagementServicesDaoImpl.class);
	
	@Override
	public List<Product> getProducts(ProductManagementRequest productsManagementRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		List<Map<Integer, Object>> productsResultSet = null;
		StringBuilder query = null;
		paramList = new ArrayList<>();
		try {
			if (!Utils.isNullOrEmptyCollection(productsManagementRequest.getProductIds())) {
				query = new StringBuilder(AbstractProductManagementServicesDao.GET_PRODUCTS);
				query.append(AbstractProductManagementServicesDao.PRODUCT_IDS);
				query = new StringBuilder(query.toString().replace("@product_ids", Utils.prepareInClauseString(productsManagementRequest.getProductIds())));
			} else {
				query = new StringBuilder(AbstractProductManagementServicesDao.GET_PRODUCTS);
			}
			if (null != productsManagementRequest.getProduct()) {
				if (!Utils.validateIfNullOrInvalidInteger(productsManagementRequest.getProduct().getId())) {
					query.append(AbstractProductManagementServicesDao.PRODUCT_ID);
					paramList.add(productsManagementRequest.getProduct().getId());
				}
				if (productsManagementRequest.isIfCallingFromAddProduct()) {
					if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getName())) {
						query.append(AbstractProductManagementServicesDao.PRODUCT_NAME);
						paramList.add(productsManagementRequest.getProduct().getName());
					}
				} else {
					if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getName())) {
						query.append(AbstractProductManagementServicesDao.PRODUCT_NAME_RESPONSIVE);
						query = new StringBuilder(query.toString().replace("@name", productsManagementRequest.getProduct().getName()));
					}
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getFlavour())) {
					query.append(AbstractProductManagementServicesDao.PRODUCT_FLAVOUR);
					paramList.add(productsManagementRequest.getProduct().getFlavour());
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getCategory())) {
					query.append(AbstractProductManagementServicesDao.PRODUCT_CATEGORY);
					paramList.add(productsManagementRequest.getProduct().getCategory());
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getCompany())) {
					query.append(AbstractProductManagementServicesDao.PRODUCT_COMPANY);
					paramList.add(productsManagementRequest.getProduct().getCompany());
				}
				if (!Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getOrgPrice())) {
					query.append(AbstractProductManagementServicesDao.PRODUCT_PRICE);
					paramList.add(productsManagementRequest.getProduct().getOrgPrice());
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getServings())) {
					query.append(AbstractProductManagementServicesDao.PRODUCT_SERVING);
					paramList.add(productsManagementRequest.getProduct().getServings());
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getWeight())) {
					query.append(AbstractProductManagementServicesDao.PRODUCT_WEIGHT);
					paramList.add(productsManagementRequest.getProduct().getWeight());
				}
				if (!Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getDiscount())) {
					query.append(AbstractProductManagementServicesDao.PRODUCTS_WITH_DISCOUNT);
					paramList.add(productsManagementRequest.getProduct().getDiscount());
				}
				
				if (!Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getFromOrgPrice())
						&& Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getToOrgPrice())) {
					query.append(AbstractProductManagementServicesDao.FROM_PRODUCT_PRICE);
					paramList.add(productsManagementRequest.getProduct().getFromOrgPrice());
				} else if (Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getFromOrgPrice())
						&& !Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getToOrgPrice())) {
					query.append(AbstractProductManagementServicesDao.TO_PRODUCT_PRICE);
					paramList.add(productsManagementRequest.getProduct().getToOrgPrice());
				} else if (!Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getFromOrgPrice())
						&& !Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getToOrgPrice())) {
					query.append(AbstractProductManagementServicesDao.PRODUCT_PRICE_RANGE);
					paramList.add(productsManagementRequest.getProduct().getFromOrgPrice());
					paramList.add(productsManagementRequest.getProduct().getToOrgPrice());
				}
				
				if (!Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getFromDiscount())
						&& Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getToDiscount())) {
					query.append(AbstractProductManagementServicesDao.PRODUCTS_WITH_DISCOUNT_FROM);
					paramList.add(productsManagementRequest.getProduct().getFromDiscount());
				} else if (Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getFromDiscount())
						&& !Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getToDiscount())) {
					query.append(AbstractProductManagementServicesDao.PRODUCTS_WITH_DISCOUNT_TO);
					paramList.add(productsManagementRequest.getProduct().getToDiscount());
				} else if (!Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getFromDiscount())
						&& !Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getToDiscount())) {
					query.append(AbstractProductManagementServicesDao.PRODUCTS_WITH_DISCOUNT_RANGE);
					paramList.add(productsManagementRequest.getProduct().getFromDiscount());
					paramList.add(productsManagementRequest.getProduct().getToDiscount());
				}
			}
			if (productsManagementRequest.isFetchProductsWithDiscountOnly()) {
				query.append(AbstractProductManagementServicesDao.PRODUCTS_WITH_DISCOUNT_ONLY);
			}
			if (!productsManagementRequest.isFetchOutOfStockProducts()) {
				query.append(AbstractProductManagementServicesDao.IS_ACTIVE_PRODUCT);
			}
			// turned off DB pagination support due to front end pagination 
//			if (!Utils.validateIfNullOrInvalidInteger(productsManagementRequest.getPageNo()) && !Utils.validateIfNullOrInvalidInteger(productsManagementRequest.getPageSize())) {
//				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to fetch orders as pageNo:[" +productsManagementRequest.getPageNo()+ "] and pageSize:[" +productsManagementRequest.getPageSize()+"]": null);
//				query.append(AbstractProductManagementServicesDao.PRODUCT_PAGINATION_SUPPORT);
//				paramList.add(((productsManagementRequest.getPageNo() - 1) * productsManagementRequest.getPageSize()));
//				paramList.add(productsManagementRequest.getPageSize());
//			}
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to fetch products by using query: " +AbstractProductManagementServicesDao.GET_PRODUCTS+ " with paramters: "+ paramList: null);
			productsResultSet = AbstractCommonDbMethods.getInstance().select(query.toString(), paramList, connection);
		} catch (Exception ex) {
			logger.warn("##Exception## while getting products ..."+ ex);
			throw new BaseException(ex);
		}
		return prepareProductsData(productsResultSet);
	}
	
	private static List<Product> prepareProductsData(List<Map<Integer, Object>> productsResultSet) throws BaseException {
		List<Product> products = new ArrayList<>();
		Product product = null;
		int index = 0;
		try {
			if (null != productsResultSet) {
				for (Map<Integer, Object> productRow : productsResultSet) {
					product = new Product();
					product.setId(null != productRow.get(++index) ? (Integer)productRow.get(index): null);
					product.setName(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setCompany(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setCategory(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setFlavour(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setQuantity(null != productRow.get(++index) ? (Integer)productRow.get(index): null);
					product.setWeight(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setWeightInKgs(null != product.getWeight() ? String.valueOf(Utils.getWeightInKilograms(product.getWeight())): null);
					product.setServings(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setServingSize(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setPurchasePrice(null != productRow.get(++index) ? ((BigDecimal)productRow.get(index)).doubleValue(): null);
					product.setOrgPrice(null != productRow.get(++index) ? ((BigDecimal)productRow.get(index)).doubleValue(): null);
					product.setDiscount(null != productRow.get(++index) ? ((BigDecimal)productRow.get(index)).doubleValue(): null);
					product.setRtlPrice(Utils.getRetailPrice(product.getOrgPrice(), product.getDiscount()));
					product.setMfgDate(null != productRow.get(++index) ? (Date)productRow.get(index): null);
					product.setExpiryDate(null != productRow.get(++index) ? (Date)productRow.get(index): null);
					product.setBarCode(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setDirectiontoUse(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setDescription(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setCreatedOn(null != productRow.get(++index) ? ((Date)productRow.get(index)): null);
					product.setLastUpdatedOn(null != productRow.get(++index) ? ((Date)productRow.get(index)): null);
					product.setImagePath1(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setImagePath2(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setImagePath3(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setImagePath4(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setImagePath5(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setIsActive(null != productRow.get(++index) ? (CommonConstants.OPTION_Y.equalsIgnoreCase((String)productRow.get(index)) ? CommonConstants.OPTION_Y : CommonConstants.OPTION_N): null);
					products.add(product);
					index = 0;
				}
			}
	} catch (Exception ex) {
		logger.warn("##Exception## while preparing product data ..."+ ex);
		throw new BaseException(ex);
	}
		return products;
	}
	
	@Override
	public void addProduct(ProductManagementRequest productsManagementRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		try {
			if (null != productsManagementRequest && null != productsManagementRequest.getProduct()) {
				paramList = new ArrayList<>();
				paramList.add(productsManagementRequest.getProduct().getName());
				paramList.add(productsManagementRequest.getProduct().getCompany());
				paramList.add(productsManagementRequest.getProduct().getCategory());
				paramList.add(productsManagementRequest.getProduct().getFlavour());
				paramList.add(productsManagementRequest.getProduct().getQuantity());
				paramList.add(productsManagementRequest.getProduct().getWeight());
				paramList.add(productsManagementRequest.getProduct().getServings());
				paramList.add(productsManagementRequest.getProduct().getServingSize());
				paramList.add(productsManagementRequest.getProduct().getPurchasePrice());
				paramList.add(productsManagementRequest.getProduct().getOrgPrice());
				paramList.add(productsManagementRequest.getProduct().getDiscount());
				paramList.add(productsManagementRequest.getProduct().getMfgDate());
				paramList.add(productsManagementRequest.getProduct().getExpiryDate());
				paramList.add(productsManagementRequest.getProduct().getBarCode());
				paramList.add(productsManagementRequest.getProduct().getDirectiontoUse());
				paramList.add(productsManagementRequest.getProduct().getDescription());
				paramList.add(productsManagementRequest.getProduct().getImagePath1());
				paramList.add(productsManagementRequest.getProduct().getImagePath2());
				paramList.add(productsManagementRequest.getProduct().getImagePath3());
				paramList.add(productsManagementRequest.getProduct().getImagePath4());
				paramList.add(productsManagementRequest.getProduct().getImagePath5());
				paramList.add(CommonConstants.OPTION_Y.equalsIgnoreCase(productsManagementRequest.getProduct().isActive()) ? CommonConstants.OPTION_Y : CommonConstants.OPTION_N);
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to insert user by using query: " +AbstractProductManagementServicesDao.ADD_PRODUCT+ " with paramters: "+ paramList: null);
				AbstractCommonDbMethods.getInstance().update(AbstractProductManagementServicesDao.ADD_PRODUCT, paramList, connection);
			}
		} catch (Exception ex) {
			logger.warn("##Exception## while adding product ..."+ ex);
			throw new BaseException(ex);
		}
	}
	
	@Override
	public void updateProduct(ProductManagementRequest productsManagementRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		StringBuilder query = null;
		StringBuilder updateColumns = new StringBuilder();
		try {
			if (null != productsManagementRequest.getProduct()) {
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getName())) {
					updateColumns.append("name = '" +productsManagementRequest.getProduct().getName()+ "',");
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getCompany())) {
					updateColumns.append("company = '" +productsManagementRequest.getProduct().getCompany()+ "',");
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getCategory())) {
					updateColumns.append("category = '" +productsManagementRequest.getProduct().getCategory()+ "',");
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getFlavour())) {
					updateColumns.append("flavour = '" +productsManagementRequest.getProduct().getFlavour()+ "',");
				}
				if (!Utils.validateIfNullOrInvalidInteger(productsManagementRequest.getProduct().getQuantity())) {
					updateColumns.append("quantity = '" +productsManagementRequest.getProduct().getQuantity()+ "',");
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getWeight())) {
					updateColumns.append("weight = '" +productsManagementRequest.getProduct().getWeight()+ "',");
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getServings())) {
					updateColumns.append("servings = '" +productsManagementRequest.getProduct().getServings()+ "',");
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getServingSize())) {
					updateColumns.append("serving_size = '" +productsManagementRequest.getProduct().getServingSize()+ "',");
				}
				if (!Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getOrgPrice())) {
					updateColumns.append("org_price = '" +productsManagementRequest.getProduct().getOrgPrice()+ "',");
				}
				if (!Utils.validateIfNullOrInvalidDouble(productsManagementRequest.getProduct().getDiscount())) {
					updateColumns.append("discount = '" +productsManagementRequest.getProduct().getDiscount()+ "',");
				}
				if (null != productsManagementRequest.getProduct().getMfgDate()) {
					updateColumns.append("mfg_date = '" +Utils.formatDate(productsManagementRequest.getProduct().getMfgDate(), CommonConstants.DATE_FORMAT_HIPHENS_YYYY_M_MDD)+ "',");
				}
				if (null != productsManagementRequest.getProduct().getExpiryDate()) {
					updateColumns.append("expiry_date = '" +Utils.formatDate(productsManagementRequest.getProduct().getExpiryDate(), CommonConstants.DATE_FORMAT_HIPHENS_YYYY_M_MDD)+ "',");
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getBarCode())) {
					updateColumns.append("bar_code = '" +productsManagementRequest.getProduct().getBarCode()+ "',");
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getDirectiontoUse())) {
					updateColumns.append("direction_to_use = '" +productsManagementRequest.getProduct().getDirectiontoUse()+ "',");
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getDescription())) {
					updateColumns.append("description = '" +productsManagementRequest.getProduct().getDescription()+ "',");
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getImagePath1())) {
					updateColumns.append("image1 = '" +productsManagementRequest.getProduct().getImagePath1()+ "',");
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getImagePath2())) {
					updateColumns.append("image2 = '" +productsManagementRequest.getProduct().getImagePath2()+ "',");
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getImagePath3())) {
					updateColumns.append("image3 = '" +productsManagementRequest.getProduct().getImagePath3()+ "',");
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getImagePath4())) {
					updateColumns.append("image4 = '" +productsManagementRequest.getProduct().getImagePath4()+ "',");
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getImagePath5())) {
					updateColumns.append("image5 = '" +productsManagementRequest.getProduct().getImagePath5()+ "',");
				}
				if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().isActive())) {
					updateColumns.append("is_active = '" + (CommonConstants.OPTION_Y.equalsIgnoreCase(productsManagementRequest.getProduct().isActive()) ? CommonConstants.OPTION_Y : CommonConstants.OPTION_N)+ "',");
				}
			}
			if (!Utils.validateIfNullOrEmptyString(updateColumns.toString().toString())) {
				query = new StringBuilder("update products set " +updateColumns.toString().substring(0, updateColumns.length() - 1)+ " where id = '" +productsManagementRequest.getProduct().getId()+ "'");
				logger.debug(logger.isDebugEnabled() ? Constants.SERVICE_NAME + "Going to insert user by using query: " +query.toString()+ " with paramters: "+ paramList: null);
				AbstractCommonDbMethods.getInstance().update(query.toString(), paramList, connection);	
			}
		} catch (Exception ex) {
			logger.warn("##Exception## while updating product ..."+ ex);
			throw new BaseException(ex);
		}
	}

	public void deleteProduct(ProductManagementRequest productsManagementRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		try {
			if (null != productsManagementRequest.getProduct()
					&& !Utils.validateIfNullOrInvalidInteger(productsManagementRequest.getProduct().getId())) {
				paramList = new ArrayList<>();
				paramList.add(productsManagementRequest.getProduct().getId());
				logger.info(logger.isInfoEnabled() ? "Going to delete product by using query: " +AbstractProductManagementServicesDao.DELETE_PRODUCT+ " with paramters: "+ paramList: null);
				AbstractCommonDbMethods.getInstance().update(AbstractProductManagementServicesDao.DELETE_PRODUCT, paramList, connection);
			}
		} catch (Exception ex) {
			logger.warn("##Exception## while deleting product ..."+ ex);
			throw new BaseException(ex);
		}
	}
}