#!/usr/bin/env bash

printf '%s\n' '哈喽 sh /Users/pengtao.geng/work/gpt-code/shell/print_a.sh; sh /Users/pengtao.geng/work/gpt-code/shell/print_b.sh'
printf '%s\n' 'a;b;c;d' | xargs -n 1 --max-procs=1 -I {} bash -c {}

#xargs 的参数 max-procs 是指定几个进程；-I 是替换，替换的符号是 {}，换成的占位符也都可以的
printf '%s\n' 'sh /Users/pengtao.geng/work/gpt-code/shell/print_a.sh; sh /Users/pengtao.geng/work/gpt-code/shell/print_b.sh' | xargs -n 1 --max-procs=2 -I {} bash -c {}

echo "==================== over"