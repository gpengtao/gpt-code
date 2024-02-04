
语法：
=VLOOKUP(lookup_value,table_array,col_index_num,[range_lookup])
lookup_value：要找的目标值，放在第一列
table_array：查找的范围，匹配的值也放在第一列
col_index_num：查找的范围找到后取第几列
range_lookup：是否模糊匹配，1模糊匹配、0精确匹配，一般用0

匹不到值的原因：
1、数据类型不匹配。举例：目标是文本类型，查找的范围是文本类型。
2、包含空格
3、包含不可见的非打印字符
