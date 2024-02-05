
## VLOOKUP
语法：
- =VLOOKUP(lookup_value,table_array,col_index_num,[range_lookup])
- lookup_value：要找的值
- table_array：查找的范围，要找的值一定要在范围的第一列
- col_index_num：查找的范围找到后取第几列
- range_lookup：是否模糊匹配，1模糊匹配、0精确匹配，一般用0

匹不到值的原因： 
1. 数据类型不匹配。比如：目标是常规类型，查找的范围是文本类型
2. 包含空格，包含不可见的非打印字符
3. 查找值不在查找区域的首列
4. 最后一个参数设置错误，0是精准匹配