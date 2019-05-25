# LC 612
#
# Runtime: 191 ms, faster than 15.79% of MySQL online submissions for Shortest Distance in a Plane.
# Memory Usage: N/A
#
# https://leetcode.com/problems/shortest-distance-in-a-plane/discuss/290611/mysql
#
# Write your MySQL query statement below
select min(round(sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2)), 2)) as shortest
from point_2d p1, point_2d p2
where p1.x != p2.x or p1.y != p2.y


# Runtime: 149 ms, faster than 47.76% of MySQL online submissions for Shortest Distance in a Plane.
# Memory Usage: N/A
#
# https://leetcode.com/problems/shortest-distance-in-a-plane/discuss/286336/Faster-than-100-in-sql-server
#
# Write your MySQL query statement below
select round(min(sqrt(power(a.x - b.x, 2) + power(a.y - b.y, 2))), 2) as shortest
from point_2d a
join point_2d b on a.x != b.x or a.y != b.y

# Input
#
# | x  | y  |
# |----|----|
# | -1 | -1 |
# | 0  | 0  |
# | -1 | -2 |
#
# Output
#
# | shortest |
# |----------|
# | 1.00     |
#
# The shortest distance is 1.00 from point (-1, -1) to (-1, -2)