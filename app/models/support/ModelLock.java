/**
 * Yobi, Project Hosting SW
 *
 * Copyright 2013 NAVER Corp.
 * http://yobi.io
 *
 * @author kjkmadness
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package models.support;

import java.util.Map;

import com.google.common.collect.MapMaker;

import play.db.ebean.Model;

public class ModelLock<T extends Model> {
    private final Map<T, Object> locks = new MapMaker().weakValues().makeMap();

    public Object get(T model) {
        synchronized (locks) {
            Object lock = locks.get(model);
            if (lock == null) {
                lock = new Object();
                locks.put(model, lock);
            }
            return lock;
        }
    }
}
