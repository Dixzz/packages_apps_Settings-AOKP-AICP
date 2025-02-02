/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.deviceinfo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.MathUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.settings.Utils;
import com.android.settings.R;

public class StorageSummaryPreference extends Preference {
    private int mPercent = -1;
    private int mSecondaryColor;

    public StorageSummaryPreference(Context context) {
        super(context);

        setLayoutResource(R.layout.storage_summary);
        setEnabled(false);

        mSecondaryColor = Utils.getColorAttr(context, android.R.attr.textColorSecondary);
    }

    public void setPercent(long usedBytes, long totalBytes) {
        mPercent = MathUtils.constrain((int) ((usedBytes * 100) / totalBytes),
                (usedBytes > 0) ? 1 : 0, 100);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder view) {
        final ProgressBar progress = (ProgressBar) view.findViewById(android.R.id.progress);
        if (mPercent != -1) {
            progress.setVisibility(View.VISIBLE);
            progress.setProgress(mPercent);
            progress.setScaleY(7f);
        } else {
            progress.setVisibility(View.GONE);
        }

        final TextView summary = (TextView) view.findViewById(android.R.id.summary);
/*
        summary.setTextColor(Color.parseColor("#8a000000"));
*/

        super.onBindViewHolder(view);
    }
}
