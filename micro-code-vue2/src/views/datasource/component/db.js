export const databaseList = [
  {
    label: 'REST API',
    value: 'rest',
    url: 'http://127.0.0.1:8080'
  },
  {
    label: 'MySQL',
    value: 'mysql',
    url: 'jdbc:mysql://127.0.0.1:3306/database?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai'
  },
  {
    label: 'PostgreSQL',
    value: 'pg',
    url: 'jdbc:postgresql://127.0.0.1:5432/database'
  },

  {
    label: 'SQL Server',
    value: 'mssql',
    url: 'jdbc:sqlserver://127.0.0.1:1433;SelectMethod=cursor;DatabaseName=database'
  },
  {
    label: 'ORACLE',
    value: 'oracle',
    url: 'jdbc:oracle:thin:@127.0.0.1:1521:ORCL'
  }
]
