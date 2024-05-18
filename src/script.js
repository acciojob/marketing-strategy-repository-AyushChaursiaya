const markingStrategies = [];

function addStrategy(name, description, target_Audience, budget, potential_ROI) {
  if(markingStrategies.find(strategy => strategy.name === name)) 
    return `Strategy with name ${name} already exists.`;

  const strategy = {name, description, target_Audience, budget, potential_ROI};
  markingStrategies.push(strategy);
  return `Strategy ${name} added successfully.`;
}

function fetchStrategyByName(name) {
  const strategy = markingStrategies.find(strategy => strategy.name === name);
  if(strategy) {
    return `${strategy.name}: ${strategy.description} for target audience ${strategy.target_Audience}. $${strategy.budget}, $${strategy.potential_ROI}`
  }else{
    return `Strategy with name ${name} not found.`;
  }
}

function updateStrategy(name, newDetails) {
  const strategy = markingStrategies.find(strategy => strategy.name === name);
  if(strategy) {
    strategy.description = newDetails.description || strategy.description;
    strategy.target_Audience = newDetails.target_Audience || strategy.target_Audience;
    strategy.budget = newDetails.budget || strategy.budget;
    strategy.potential_ROI = newDetails.potential_ROI || strategy.potential_ROI; 
    return `Strategy ${name} updated successfully.`;
  }else {
    return `Strategy with name ${name} not found.`;
  }
}


function deleteStrategy(name) {
  const index = markingStrategies.findIndex(strategy => strategy.name === name);
  if(index !== -1) {
    markingStrategies.splice(index, 1);
    return `Strategy ${name} deleted successfully.`;
  }else {
    return `Strategy with name ${name} not found.`;
  }
} 

function listStrategiesByBudgetRange(minBudget, maxBudget) {
  const strategiesInRange = markingStrategies.filter(strategy => strategy.budget >= minBudget && strategy.budget <= maxBudget);
  if (strategiesInRange.length > 0) {
      return strategiesInRange.map(strategy => `${strategy.name}: Budget: $${strategy.budget}`).join("\n");
  } else {
      return `No strategies found within the budget range $${minBudget} - $${maxBudget}.`;
  }
}

console.log(addStrategy("Social Media Blitz", "Focus on promoting via Facebook and Instagram", "Ages 18-30", 10000, 50000));

console.log(fetchStrategyByName('Social Media Blitz'));

console.log(updateStrategy("Social Media Blitz", { description: "Updated description", budget: 12000 }));

console.log(deleteStrategy("Social Media Blitz"));

console.log(addStrategy("Email Campaign", "Promoting via emails", "All ages", 5000, 20000));
console.log(listStrategiesByBudgetRange(4000, 10000));