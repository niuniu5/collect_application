import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler
from sklearn.neighbors import KNeighborsClassifier
from joblib import dump, load

# 读取数据
data = pd.read_csv('acl_score_record.csv')  #
# 显示前几行数据
print(data.head())
# 检查缺失值
print(data.isnull().sum())
# 处理缺失值
data.fillna(data.mean(), inplace=True)

# 检查数据类型
print(data.dtypes)
# 确保数据类型正确
data['low_scores'] = data['low_scores'].astype(float)
data['low_rank'] = data['low_rank'].astype(int)

# 提取特征和标签
X = data[['low_scores', 'low_rank', 'province_id']]
y = data[['college_id', 'major_id']]

# 归一化特征
scaler = MinMaxScaler()
X_scaled = scaler.fit_transform(X)

# 将归一化后的特征重新赋值给DataFrame
X = pd.DataFrame(X_scaled, columns=['low_scores', 'low_rank', 'province_id'])

# 划分训练集和测试集
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
print('X_train----', X_train)
print('X_test----', X_test)
print('y_train----', y_train)
print('y_test----', y_test)

# 创建KNN分类器
knn = KNeighborsClassifier(n_neighbors=5)

# 训练模型
knn.fit(X_train, y_train)
print('X_test----------', X_test)
# 保存模型
dump(knn, 'University_prediction.joblib')

# 新学生高考数据
new_student_data = {
    'province_id': [1],  # 省份id
    'low_scores': [100],  # 高考分
    'low_rank': [10000]  # 高考排位
}
# 将新学生数据转换为DataFrame
new_student_df = pd.DataFrame(new_student_data)
# 对新学生数据进行归一化
new_student_scaled = scaler.transform(new_student_df)

probabilities = knn.predict_proba(new_student_scaled)
print("概率分布:", probabilities)

# 返回多个预测结果
top_k = 10  # 返回前 k 个预测结果
top_indices = np.argsort(probabilities, axis=1)[:, ::-1][:, :top_k]
top_labels = top_indices[:, ::-1]
top_probabilities = np.sort(probabilities, axis=1)[:, ::-1][:, :top_k]

print("前 10 个预测结果及其概率:")
for i in range(len(new_student_scaled)):
    print(f"Sample {i + 1}:")
    for j in range(top_k):
        print(f"  Label: {top_labels[i][j]}, Probability: {top_probabilities[i][j]:.2f}")

# 根据训练好的模型预测
knn_loaded = load('University_prediction.joblib')
y_pred = knn_loaded.predict(new_student_scaled)
print('y_pred----------', y_pred)
