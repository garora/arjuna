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

from arjuna.tpi.markup import *
from arjuna.tpi.markup_helpers import *


@test_function
def assert_nothingness(my):
    validator = my.steps.validate("Higher purpose")
    validator.assert_that(None).is_none()
    validator.assert_that(1).is_not_none()


@test_function
def assert_none_fails_for_notnone(my):
    my.steps.validate("Higher purpose").assert_that(1).is_none()


@test_function
def assert_notnone_fails_for_none(my):
    my.steps.validate("Higher purpose").assert_that(None).is_not_none()

