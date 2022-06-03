#!/bin/bash
cd ..
cd gem5
build/X86/gem5.opt -q configs/example/se.py --cmd=tests/test-progs/t1/t1 --cpu-type=TimingSimpleCPU --l1d_size=64kB --l1i_size=16kB --caches -o $1
