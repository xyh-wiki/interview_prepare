一个线上服务器访问日志格式如下（用sql答题）
时间                    接口                         ip地址
2016-11-09 11：22：05    /api/user/login                  110.23.5.33
2016-11-09 11：23：10    /api/user/detail                  57.3.2.16
.....
2016-11-09 23：59：40    /api/user/login                  200.6.5.166
求11月9号下午14点（14-15点），访问api/user/login接口的top10的ip地址
数据集
2016-11-09 14:22:05	/api/user/login	110.23.5.33
2016-11-09 11:23:10	/api/user/detail	57.3.2.16
2016-11-09 14:59:40	/api/user/login	200.6.5.166
2016-11-09 14:22:05	/api/user/login	110.23.5.34
2016-11-09 14:22:05	/api/user/login	110.23.5.34
2016-11-09 14:22:05	/api/user/login	110.23.5.34
2016-11-09 11:23:10	/api/user/detail	57.3.2.16
2016-11-09 23:59:40	/api/user/login	200.6.5.166
2016-11-09 14:22:05	/api/user/login	110.23.5.34
2016-11-09 11:23:10	/api/user/detail	57.3.2.16
2016-11-09 23:59:40	/api/user/login	200.6.5.166
2016-11-09 14:22:05	/api/user/login	110.23.5.35
2016-11-09 14:23:10	/api/user/detail	57.3.2.16
2016-11-09 23:59:40	/api/user/login	200.6.5.166
2016-11-09 14:59:40	/api/user/login	200.6.5.166
2016-11-09 14:59:40	/api/user/login	200.6.5.166

-- 建表
create table ip(
    time string,
    interface string,
    ip string)
row format delimited fields terminated by '\t';

-- 最终 sql
select
  ip,
  interface,
  count(*) ct
from
  ip
where