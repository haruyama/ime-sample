# ime-sample

This is a clojure implementation of 'https://gihyo.jp/assets/files/book/2012/978-4-7741-4993-6/download/imebook-sample.tar.gz'

I have not yet implemented 'train' and 'eval'.

## usage

### dictionary and model

* download, uncompress and extract 'https://gihyo.jp/assets/files/book/2012/978-4-7741-4993-6/download/imebook-sample.tar.gz'
* change directory to 'imebook-sample'
* run 'ruby learn.rb train.cps --verbose > train.log'

### run ime-sample

* install https://github.com/technomancy/leiningen
* git clone https://github.com/haruyama/ime-sample.git
* change directory to 'ime-sample'
* copy 'mk.model' and 'juman.dic' from imebook-sample directory
* lein trampoline run -m ime.test
* input ひらがな to stdio

## License

  Copyright (c) HARUYAMA Seigo

 Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

   1. Redistributions of source code must retain the above Copyright
notice, this list of conditions and the following disclaimer.

   2. Redistributions in binary form must reproduce the above
Copyright notice, this list of conditions and the following disclaimer
in the documentation and/or other materials provided with the
distribution.

   3. Neither the name of the authors nor the names of its
contributors may be used to endorse or promote products derived from
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

## Original License

  Copyright (c) 2012, TOKUNAGA Hiroyuki

 Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

   1. Redistributions of source code must retain the above Copyright
notice, this list of conditions and the following disclaimer.

   2. Redistributions in binary form must reproduce the above
Copyright notice, this list of conditions and the following disclaimer
in the documentation and/or other materials provided with the
distribution.

   3. Neither the name of the authors nor the names of its
contributors may be used to endorse or promote products derived from
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
