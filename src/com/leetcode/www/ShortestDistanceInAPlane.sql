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