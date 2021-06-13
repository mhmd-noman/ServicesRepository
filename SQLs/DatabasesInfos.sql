CREATE TABLE databases_info (
    id INT AUTO_INCREMENT,
    db_code varchar(80) UNIQUE,
    name varchar(80),   
    url varchar(255),
    user varchar(80),
    password varchar(80),
    api_base_uri varchar(80),
    content_type varchar(80),
    token_prefix varchar(20),
    token varchar(250),
    auth_username varchar(80),
    auth_password varchar(80),
    PRIMARY KEY(id, db_code)
);

DROP TABLE databases_info;

select * from databases_info
INSERT INTO  databases_info  (db_code, name, url, user, password) VALUES ('MX_FIT', 'maximafitness', 'jdbc:mysql://localhost:3306/maximafitness', 'root', 'Temp/123');

select id, db_code, name, url, user, password from databases_info where db_code = 'MX_FIT';

select * from databases_info

INSERT INTO databases_info (db_code, name, url, user, password) VALUES ('MX_FIT', 'maximafitness', 'jdbc:mysql://localhost:3306/maximafitness', 'root', 'Temp/123');
INSERT INTO databases_info (db_code, name, url, user, password) VALUES ('UFN', 'ultrafitness', 'jdbc:mysql://localhost:3306/ultrafitness', 'root', 'Temp/123');


INSERT INTO databases_info (db_code, name, url, user, password) VALUES ('MX_FIT', 'maximafitness', 'jdbc:mysql://134.122.14.40:3306/maximafitness', 'rootadmin', 'Temp/123');
INSERT INTO databases_info (db_code, name, url, user, password) VALUES ('UFN', 'ultrafitness', 'jdbc:mysql://134.122.14.40:3306/ultrafitness', 'rootadmin', 'Temp/123');


INSERT INTO databases_info (id, db_code, name, url, user, password, api_base_uri, content_type, token_prefix, token, auth_username, auth_password) VALUES (1, 'MX_FIT', 'maximafitness', 'jdbc:mysql://134.122.14.40:3306/maximafitness', 'rootadmin', 'Temp/123', null, null, null, null, null, null);
INSERT INTO databases_info (id, db_code, name, url, user, password, api_base_uri, content_type, token_prefix, token, auth_username, auth_password) VALUES (2, 'UFN', 'ultrafitness', 'jdbc:mysql://134.122.14.40:3306/ultrafitness', 'rootadmin', 'Temp/123', 'http://134.122.14.40:8080/iteam/services/', 'application/json', 'Bearer ', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtbm9tYW4iLCJleHAiOjE2MTg4NjU2MzgsImlhdCI6MTYxODI2MDgzOH0.Be7YJbXba8s-TAxQacVydEeQtgQkflhnqSNxikBXKjpt1EizXOyDeW-CW_BnkPiwMCsPs9arWW-A7f8BFWvOAw', 'mnoman', 'Temp/mnoman123');

select id, db_code, name, url, user, password, api_base_uri, content_type, token_prefix, token, auth_username, auth_password from databases_info where db_code = ?
public static final String FETCH_DATABASE = "select id, db_code, name, url, user, password, api_base_uri, content_type, token_prefix, token, auth_username, auth_password from databases_info where db_code = ?";










@Override
	public ApiInfo getApiInfo(String clientIdentifier, Connection connection) throws BaseException {
		List<Object> paramList = null;
		List<Map<Integer, Object>> dbResultSet = null;
		try {
			paramList = new ArrayList<>();		
			if (Utils.validateIfNullOrEmptyString(clientIdentifier)) {
				logger.info(logger.isInfoEnabled() ? "DbCode has been provided null/empty!": null);
				return null;
			}
			paramList.add(clientIdentifier);
			logger.info(logger.isInfoEnabled() ? "Going to fetch apiInfo by using query: " +AbstractApiCommunicationMethods.FETCH_API_INFO+ " with paramters: "+ paramList: null);
			dbResultSet = select(AbstractApiCommunicationMethods.FETCH_API_INFO, paramList, connection);
		} catch (Exception ex) {
			logger.warn("##Exception## while deleting product ...");
			throw new BaseException(ex);
		}
		return prepareDbData(dbResultSet);
	}