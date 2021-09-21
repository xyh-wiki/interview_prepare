-- HQL01
表结构：uid,subject_id,score
求：找出所有科目成绩都大于某一学科平均成绩的学生
数据集如下
uid  subject_id   score
1001	01	       90
1001	02	       90
1001	03	       90
1002	01	       85
1002	02	       85
1002	03	       70
1003	01	       70
1003	02	       70
1003	03	       85
-- 建表语句:
create table score(
uid String,
subject_id String,
score int)
row format delimited fields terminated by '\t';

-- 求出每个学科的平均成绩
select
	uid,
	score,
	avg(score) over(partition by subject_id) avg_score
from 
	score; t1

-- 根据是否大于平均成绩记录 flag, 大于则记为 0 否为为 1
select
	uid,
	if(score>avg_score,0,1) flag
from
	t1;t2

-- 根据学生 id 进行分组统计 flag 的和, 和为 0 则是所有学科都大于平均成绩
select
	uid
from
	t2
group by
	uid
having
	sum(flag)=0;

-- 最终 SQL
select
	uid
from
	(select
		uid,
		if(score>avg_score,0,1) flag
	form
		(select
			uid,
			score,
			avg(score) over(partition by subject_id) avg_score
		form
		score)t1)t2
group by
	uid
having
	sum(flag)=0;