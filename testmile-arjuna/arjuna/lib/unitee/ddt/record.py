'''
This file is a part of Test Mile Arjuna
Copyright 2018 Test Mile Software Testing Pvt Ltd

Website: www.TestMile.com
Email: support [at] testmile.com
Creator: Rahul Verma

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
'''

import types

class DataRecord:
    def __init__(self, *vargs, **kwargs):
        self.__indexed = vargs
        self.__named = types.MappingProxyType({i.lower(): j for i,j in kwargs.items()})

    def value_at(self, i):
        return self.__indexed[i]

    def value_named(self, name):
        return self.__named[name.lower()]

    def indexed_values(self):
        return self.__indexed

    def named_values(self):
        return self.__named

    def __str__(self):
        parts = ["Indexed"]
        if not self.indexed_values():
            parts.append("<empty>")
        else:
            for i,v in enumerate(self.indexed_values()):
                parts.append("[{}] {}".format(i, v))
        parts.append("Named")
        if not self.named_values():
            parts.append("<empty>")
        else:
            for i,v in self.named_values().items():
                parts.append("[{}] {}".format(i, v))
        return "\n".join(parts)

    def is_empty(self):
        return not self.indexed_values() and not self.named_values()
