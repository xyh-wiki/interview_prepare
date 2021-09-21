userId	visitDate	visitCount
u01		2017/1/21		5
u02		2017/1/23		6
u03		2017/1/22		8
u04		2017/1/20		3
u01		2017/1/23		6
u01		2017/2/21		8
U02		2017/1/23		6
U01		2017/2/22		4

要求使用SQL统计出每个用户的累积访问次数，如下表所示：
用户id	月份			小计		累积
u01		2017-01		11		11
u01		2017-02		12		23
u02		2017-01		12		12
u03		2017-01		8			8
u04		2017-01		3			3

-- 创建表
create table action(
userId String,
visitDate String,
visitCount int)
row format delimited fields terminated by "\t";

-- 修改数据格式
select
	userId,
	date_format(regexp_replace(visitDate,'/','-'),'yyyy-MM') mn,
	visitCount
from
	action;t1

-- 计算每人单月访问量
select
	userId,
	mn,
	sum(visitCount) mn_count
from
	t1
group by
	userId,mn;t2

-- 按月累计访问量
select
	userId,
	mn,
	sum(mn_count) over(partition by userId order by mn)
from
	t2;

-- 最终 sql
select
	userId,
	mn,
	mn_count,
	sum(mn_count) over(partition by userId order by mn)
from
	(select
			userId,
			mn,
			sum(visitCount) mn_count
		from
			(select
					userId,
					date_format(regexp_replace(visitDate,'/','-'),'yyyy-MM') mn,
					visitCount
			 from
					action)t1)
group by userId,mn)t2;