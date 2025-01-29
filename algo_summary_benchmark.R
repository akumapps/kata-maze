library(jsonlite)
library(dbplyr)
library(tidyverse)
library(ggplot2)

args <- commandArgs(trailingOnly = TRUE)
if (length(args) == 0) {
  args <- c("metrics.json")
}

#load json results
json_file_path <- args[1]
algo_data <- fromJSON(json_file_path)


#transform json to dataframe 
output_dataframe <- as.data.frame(algo_data)
print(output_dataframe)


# make average values per algp, row, column
averaged_data <- algo_data %>%
  group_by(algorythmName, rows, columns) %>%
  summarize(
    avg_executionTime = mean(executionTime),
    avg_complexity = mean(complexity),
    avg_memoryUsage = mean(memoryUsage),
    .groups = "drop"  
  )

# Execution time
ggplot(averaged_data, aes(x = interaction(rows, columns), y = avg_executionTime, color = algorythmName, group = algorythmName)) +
  geom_point(size = 3) +  
  geom_line() +  
  labs(title = "Average Execution Time for Each Algorithm", 
       x = "Rows x Columns", 
       y = "Average Execution Time (ms)") +
  theme(axis.text.x = element_text(angle = 45, hjust = 1))


#  complexity 
ggplot(averaged_data, aes(x = interaction(rows, columns), y = avg_complexity, color = algorythmName, group = algorythmName)) +
  geom_point(size = 3) +  
  geom_line() +  
  labs(title = "Average Complexity for Each Algorithm", 
       x = "Rows x Columns", 
       y = "Average Complexity") +
  theme(axis.text.x = element_text(angle = 45, hjust = 1))

# memoryUsage 
ggplot(averaged_data, aes(x = interaction(rows, columns), y = avg_memoryUsage, color = algorythmName, group = algorythmName)) +
  geom_point(size = 3) +  
  geom_line() +  s
  labs(title = "Average Memory Usage for Each Algorithm", 
       x = "Rows x Columns", 
       y = "Average Memory Usage (bytes)") +
  theme(axis.text.x = element_text(angle = 45, hjust = 1))
